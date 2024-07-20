-- Enable the pgcrypto extension
--07/19/2024
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE roles
(
    id               UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    role_name        VARCHAR(255) UNIQUE NOT NULL,
    created_at       TIMESTAMP NOT NULL,
    updated_at       TIMESTAMP NULL
);

CREATE TABLE users
(
    id                  UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    user_name           VARCHAR(255) NOT NULL,
    email               VARCHAR(255) UNIQUE NOT NULL,
    password            VARCHAR(255) NOT NULL,
    role_id             UUID,
    created_at          TIMESTAMP  NOT NULL,
    updated_at          TIMESTAMP  NOT NULL,
    CONSTRAINT fk_role_id FOREIGN KEY(role_id) REFERENCES roles(id)
   
);
INSERT INTO roles (role_name, created_at, updated_at) VALUES
('Front-end Sales Manager', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Front-end Sales', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Product Manager', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sales Team member', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sales Key User', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Regional CRM IT', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Regional Pricing Admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('BG Marketing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('BU Marketing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('BG Marketing Manager', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


