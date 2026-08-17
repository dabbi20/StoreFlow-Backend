create table sales(
                      id BIGSERIAL primary key,
                      product_id BIGINT not null,
                      cantidad INT not null check(cantidad >0),
                      precio_unitario DECIMAL(12,2) check(precio_unitario > 0) not null,
                      total DECIMAL(12,2) check (total > 0) not null,
                      fecha_venta timestamp not null DEFAULT CURRENT_TIMESTAMP,



                      constraint fk_products
                          foreign KEY(product_id)
                              references products(id)
                              on delete RESTRICT

)
