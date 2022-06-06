package me.veir1.minimarket.product;

// Me dio lata documentar este xd
public final class Product { //
    private final String productName;
    private final int quantity;
    private final int price;

    public Product(final String productName, final int quantity, final int price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
