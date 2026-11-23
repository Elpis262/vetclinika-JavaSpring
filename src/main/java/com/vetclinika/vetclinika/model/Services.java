package com.vetclinika.vetclinika.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название услуги обязательно")
    @Size(min = 2, max = 100, message = "Название от 2 до 100 символов")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Цена обязательна")
    @DecimalMin(value = "0.0", message = "Цена не может быть отрицательной")
    @Column(name = "price")
    private BigDecimal price;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
}