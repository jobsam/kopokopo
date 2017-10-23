-- app_keys table will be holding  all the app keys searched using the key_name
CREATE TABLE IF NOT EXISTS app_keys (
id serial NOT NULL ,
key_name VARCHAR (20) NOT NULL ,
key_value VARCHAR (150) NOT NULL ,
created_at TIMESTAMP DEFAULT current_timestamp,
updated_at TIMESTAMP NULL
);
