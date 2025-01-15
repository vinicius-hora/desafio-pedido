CREATE TABLE IF NOT EXISTS item (
                                    id BIGSERIAL PRIMARY KEY,
                                    codigo_pedido VARCHAR(255),
    produto VARCHAR(255),
    quantidade INTEGER,
    preco DOUBLE PRECISION,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS pedido (
                                      id BIGSERIAL PRIMARY KEY,
                                      codigo_pedido VARCHAR(255),
    codigo_cliente VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );