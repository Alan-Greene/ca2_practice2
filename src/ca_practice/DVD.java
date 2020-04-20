package ca_practice;

public class DVD {
    //Member variables
    private String title, category;
    private Integer year, quantity_in_stock, quantity_checked_out, rating_count;
    private Double rating_total;
    final Integer NUM_COPIES = 5;

    //Constructor
    public DVD(String title, String category, Integer year, Integer quantity_in_stock, Integer quantity_checked_out){
        this.title = title;
        this.category = category;
        this.year = year;
        this.quantity_in_stock = quantity_in_stock;
        this.quantity_checked_out = quantity_checked_out;
        this.rating_total = 0.0;
        this.rating_count = 0;
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getQuantity_in_stock() {
        return quantity_in_stock;
    }

    public Integer getQuantity_checked_out() {
        return quantity_checked_out;
    }

    public Integer getRating_count() {
        return rating_count;
    }

    public Double getRating_total() {
        return rating_total;
    }

    public Integer getNUM_COPIES() {
        return NUM_COPIES;
    }

    public void rateDvd(Double rating){
        if (rating >=0 && rating <= 5){
            rating_total += rating;
            rating_count++;
        } else {
            System.out.println("The rating must be between 1 and 5.");
        }
    }

    public Double getAverageRating() throws ArithmeticException{
        if (rating_count == 0){
            throw new ArithmeticException("There are 0 ratings for: " + title);
        }
        return rating_total / rating_count;
    }

    public void checkOut() throws OutOfStockException{
        if (quantity_in_stock == 0){
            throw new OutOfStockException("We are currently out of stock.");
        } else {
            quantity_in_stock -= 1;
            quantity_checked_out += 1;
        }
    }

    public Boolean returnDvd(){
        if (quantity_in_stock + 1 > NUM_COPIES || quantity_checked_out < 1){
            System.out.println("DVD does not belong to this store");
            return false;
        } else {
            quantity_in_stock ++;
            quantity_checked_out --;
            return true;
        }
    }

    public String toString(){

        StringBuilder builder = new StringBuilder();
        builder.append(title);
        builder.append(" ");
        builder.append(category);
        builder.append(" ");
        builder.append(year);
        builder.append(" ");
        builder.append(quantity_in_stock);
        builder.append(" ");
        builder.append(quantity_checked_out);
        builder.append(" ");
        builder.append(rating_total);
        builder.append(" ");
        builder.append(rating_count);


        return builder.toString();
    }




}
