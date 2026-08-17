create table products(
                         id BIGSERIAL primary KEY ,
                         nombre VARCHAR(150)not null unique,
                         precio DECIMAL(12,2) not null check(precio > 0),
                         stock INT not null check (stock >= 0),
                         fecha_creacion TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP,
                         fecha_actualizacion TIMESTAMP not null DEFAULT CURRENT_TIMESTAMP
)
