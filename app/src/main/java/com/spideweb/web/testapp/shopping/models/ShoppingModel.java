package com.spideweb.web.testapp.shopping.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ShoppingModel {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("price")
        @Expose
        private Double price;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("rating")
        @Expose
        private Rating rating;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Rating getRating() {
            return rating;
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

    public class Rating {

        @SerializedName("rate")
        @Expose
        private Double rate;
        @SerializedName("count")
        @Expose
        private Integer count;

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

    }

}

