package Model;

import java.util.Objects;

public class Seller {
        public int sellerId;
        public String name;
        public Seller(){

        }

        public Seller(int sellerId, String name) {
            this.sellerId = sellerId;
            this.name = name;
        }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return sellerId == seller.sellerId && Objects.equals(name, seller.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, name);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", name='" + name + '\'' +
                '}';
    }
}


