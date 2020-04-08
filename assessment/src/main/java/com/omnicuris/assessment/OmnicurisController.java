package com.omnicuris.assessment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.omnicuris.dao.OrdersRepo;
import com.omnicuris.dao.ProductRepo;
import com.omnicuris.model.OrderRequest;
import com.omnicuris.model.Orders;
import com.omnicuris.model.Product;
import com.omnicuris.model.Response;

@Controller
public class OmnicurisController {

	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	OrdersRepo ordersRepo;

	@PostMapping("/add/item")
	@ResponseBody
	public Response addProduct(@RequestBody Product product) {
		Response response = new Response();
		try {
			if (product == null || product.getItemName() == null || product.getCategoryId() == null
					|| product.getAvailQuantity() == null || product.getPricePerUnit() == null) {
				throw new Exception();
			}
			productRepo.save(product);
			response.setSuccess(1);
			response.setMessage("Item Added Successfully");
			response.setData(product);
		} catch (Exception e) {
			response.setSuccess(0);
			response.setMessage("Some Internal Error");
		}	
		return response;
	}

	
	@GetMapping("/get/item/{itemId}")
	@ResponseBody
	public Response getItemDetails(@PathVariable("itemId") int itemId) {
		Response response = new Response();
		try {
			if(itemId<=0)
				throw new Exception();
			Product product = productRepo.findByItemId(itemId);
			if(product == null)
				throw new Exception();
				
			response.setSuccess(1);
			response.setData(product);
		} catch(Exception e) {
			response.setSuccess(0);
			response.setMessage("Item Not Found");
		}
		return response;
	}
	
	@DeleteMapping("delete/item/{itemId}")
	@ResponseBody
	public Response deleteItem(@PathVariable("itemId") int itemId) {
		Response response = new Response();
		try {
			if(itemId<=0)
				throw new Exception();
			productRepo.deleteById(itemId);
			response.setSuccess(1);
			response.setMessage("Item Deleted Successfully");
		} catch(Exception e) {
			response.setSuccess(0);
			response.setMessage("Item Not Found");
		}
		return response;
	}
	
	@PostMapping("/update/item")
	@ResponseBody
	public Response updateProduct(@RequestBody Product product) {
		Response response = new Response();
		try {
			if(product == null || product.getItemId()<=0)
				throw new Exception();
			
			Product itemDetail = productRepo.findByItemId(product.getItemId());
			if(itemDetail == null) {
				response.setSuccess(0);
				response.setMessage("Item Not Found");
				return response;
			}
			
			if(product.getItemName() != null) 
				itemDetail.setItemName(product.getItemName());
			
			if(product.getCategoryId() != null) 
				itemDetail.setCategoryId(product.getCategoryId());
			
			if(product.getAvailQuantity() != null) 
				itemDetail.setAvailQuantity(product.getAvailQuantity());
			
			if(product.getPricePerUnit() != null) 
				itemDetail.setPricePerUnit(product.getPricePerUnit());
			
			productRepo.save(itemDetail);
			response.setSuccess(1);
			response.setMessage("Item Updated Successfully");
			response.setData(itemDetail);
		} catch (Exception e) {
			response.setSuccess(0);
			response.setMessage("Some Internal Error");
		}	
		return response;
	}
	
	
	@GetMapping("/get/items")
	@ResponseBody
	public Response getAllItems() {
		Response response = new Response();
		try {
			List<Product> product = productRepo.findAll();
			if(product.isEmpty())
				throw new Exception();
				
			response.setSuccess(1);
			response.setData(product);
		} catch(Exception e) {
			response.setSuccess(0);
			response.setMessage("Item Not Found");
		}
		return response;
	}
	
	
	@PostMapping("/order/place")
	@ResponseBody
	public Response placeOrder(@RequestBody OrderRequest orderRequest) {
		Response responseBody = new Response();
		try {
			ArrayList<Response> responseList = new ArrayList<Response>();
			if(orderRequest.getUserEmailId() == null || "".equals(orderRequest.getUserEmailId()) || orderRequest.getOrderList() == null) {
				responseBody.setSuccess(0);
				responseBody.setMessage("Invalid Info");
				return responseBody;
			}
			
			for (Orders requestOrder : orderRequest.getOrderList()) {
				Response response = new Response();
				if (requestOrder == null || requestOrder.getItemId() == null || requestOrder.getNoOfItems() == null) {
					response.setSuccess(0);
					response.setMessage("Invalid Info");
					responseList.add(response);
					continue;
				}

				Product productDetails = productRepo.findByItemId(requestOrder.getItemId());
				if (productDetails != null) {
					if (productDetails.getAvailQuantity() < requestOrder.getNoOfItems()) {
						response.setSuccess(0);
						response.setMessage(
								"Could Not Place Order. Available Quantity is: " + productDetails.getAvailQuantity());
						responseList.add(response);
						continue;
					}

					Double totalPrice = (double) (requestOrder.getNoOfItems() * productDetails.getPricePerUnit());
					requestOrder.setPrice(totalPrice);
					requestOrder.setUserEmailId(orderRequest.getUserEmailId());
					productDetails.setAvailQuantity(productDetails.getAvailQuantity() - requestOrder.getNoOfItems());
					productRepo.save(productDetails);
					ordersRepo.save(requestOrder);
					response.setSuccess(1);
					response.setMessage("Order Placed Successfully");
					response.setData(requestOrder);
					responseList.add(response);
				} else {
					response.setSuccess(0);
					response.setMessage("Item Not Found");
					responseList.add(response);
				}

			}
			
			responseBody.setData(responseList);
		} catch (Exception e) {
			responseBody.setSuccess(0);
			responseBody.setMessage("Some Internal Error");
		}
		return responseBody;
	}
	
	@GetMapping("/get/orders")
	@ResponseBody
	public Response getAllOrders() {
		Response response = new Response();
		try {
			List<Orders> orders = ordersRepo.findAll();
			if(orders.isEmpty())
				throw new Exception();
				
			response.setSuccess(1);
			response.setData(orders);
		} catch(Exception e) {
			response.setSuccess(0);
			response.setMessage("Orders Not Found");
		}
		return response;
	}

}