package com.makuno.memory.data.models

import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Variants (

	@SerializedName("id") val id : Long,
	@SerializedName("product_id") val productId : Long,
	@SerializedName("title") val title : String,
	@SerializedName("price") val price : Double,
	@SerializedName("sku") val sku : String,
	@SerializedName("position") val position : Int,
	@SerializedName("inventory_policy") val inventoryPolicy : String,
	@SerializedName("compare_at_price") val compareAtPrice : String,
	@SerializedName("fulfillment_service") val fulfillmentService : String,
	@SerializedName("inventory_management") val inventoryManagement : String,
	@SerializedName("option1") val option1 : String,
	@SerializedName("option2") val option2 : String,
	@SerializedName("option3") val option3 : String,
	@SerializedName("created_at") val createdAt : String,
	@SerializedName("updated_at") val updatedAt : String,
	@SerializedName("taxable") val taxable : Boolean,
	@SerializedName("barcode") val barcode : String,
	@SerializedName("grams") val grams : Int,
	@SerializedName("image_id") val imageId : String,
	@SerializedName("weight") val weight : Double,
	@SerializedName("weight_unit") val weightUnit : String,
	@SerializedName("inventory_item_id") val inventoryItemId : Long,
	@SerializedName("inventory_quantity") val inventoryQuantity : Int,
	@SerializedName("old_inventory_quantity") val oldInventoryQuantity : Int,
	@SerializedName("requires_shipping") val requiresShipping : Boolean,
	@SerializedName("admin_graphql_api_id") val adminGraphqlApiId : String
)