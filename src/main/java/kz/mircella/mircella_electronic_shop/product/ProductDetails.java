package kz.mircella.mircella_electronic_shop.product;

import lombok.*;

import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDetails {
    private String title;
    private String path;
    private double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetails)) return false;
        ProductDetails that = (ProductDetails) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getPath(), that.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getPath(), getPrice());
    }
}
