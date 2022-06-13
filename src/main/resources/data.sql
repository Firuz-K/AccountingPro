insert into users(id, created_by, created_time, is_deleted, updated_by, updated_time, email, enabled, first_name,
                  last_name, password, phone, company_id, role_id)
values (1, 1, '2021-01-05 00:00:00', false, 1, '2021-01-15 00:00:00', 'admin@admin.com', true, 'Mike', 'Smith', null,
        '7894561231', null, null),
       (2, 2, '2021-01-06 00:00:00', false, 2, '2021-01-15 00:00:00', 'manager@admin.com', true, 'Mikey', 'Smith', null,
        '7894561230', null, null),
       (3, 2, '2021-01-07 00:00:00', false, 3, '2021-01-15 00:00:00', 'employee@admin.com', true, 'Micheal', 'Smith',
        null, '7894561235', null, null);

-- Company sample data
insert into companies(created_by,updated_by, created_time, is_deleted, updated_time, address1, address2, email,
                      enabled, establishment_date, phone, representative, state, title, zip)
VALUES (1,1, '2021-01-05 00:00:00', false, '2021-01-05 00:00:00', '3073 Sumner Street', '', 'abc@fuji.com', true,
        '2020-01-05', '310-628-7886', 'John Fuji', 'CA', 'Fuji Holding', '91752'),
       (1,1,  '2021-01-05 00:00:00', false, '2021-01-05 00:00:00', '308 Edwards Street', '', 'abc@hp.com', true,
        '2020-01-05', '252-794-1258', 'John HP', 'NY', 'HP', '27983'),
       (1,1,  '2021-01-05 00:00:00', false, '2021-01-05 00:00:00', '3868 Franklee Lane', '', 'abc@unilever.com',
        true, '2020-01-05', '484-556-6097', 'John Unilever', 'PA', 'Unilever', '19714'),
       (1,1, '2021-01-05 00:00:00', false, '2021-01-05 00:00:00', '1116 Cooks Mine Road', '', 'abc@bp.com',
        true, '2020-01-05', '435-215-6440', 'John BP', 'UT', 'BP', '84104');

INSERT INTO roles(enabled, name)
values(true, 'Admin'),
      (true, 'Manager'),
      (true, 'Employee'),
      (true, 'Root');

insert into client_vendor(created_by, created_time, enabled, is_deleted, updated_by, updated_time, address, company_name, email, phone, company_type, zip_code, company_id, state)
VALUES (1,'2021-01-05 00:00:00', true,false,1,'2021-01-05 00:00:00','12 Hope st','Z Vendor','aaa@zvendor.com','111-222-3333','VENDOR','11223',1,'NY'),
       (1,'2021-01-05 00:00:00', true,false,1,'2021-01-05 00:00:00','12 Kunt st','X Client','aaa@xclient.com','111-222-3333','CLIENT','11223',1,'NY');

insert into categories(created_by, created_time, is_deleted, updated_by, updated_time, description, enabled,company_id)
values (1, '2021-01-05 00:00:00', false, 1, '2021-01-05 00:00:00', 'Furniture', true,1) ,
       (1, '2021-01-05 00:00:00', false, 1, '2021-01-05 00:00:00', 'Phone', true,1),
       (2, '2021-01-05 00:00:00', false, 1, '2021-01-05 00:00:00', 'Clothing', true,1),
       (3, '2021-01-05 00:00:00', false, 1, '2021-01-05 00:00:00', 'Electronics', true,1),
       (2, '2021-01-05 00:00:00', false, 1, '2021-01-05 00:00:00', 'Appliances', true,1);

insert into products(created_by, created_time, enabled, is_deleted, updated_by, updated_time, description, low_limit_alert, name, product_status, quantity, tax, unit, category_id, company_id)
VALUES (1,'2021-01-05 00:00:00',true,false,1,'2021-01-05 00:00:00','mobile phone',10,'iPhone14','ACTIVE',10,10,'PC',2,1),
       (1,'2021-01-05 00:00:00',true,false,1,'2021-01-05 00:00:00','mobile phone',10,'iPhone13','OUT_OF_STOCK',0,10,'PC',2,1),
       (1,'2021-01-05 00:00:00',true,false,1,'2021-01-05 00:00:00','mobile phone',10,'iPhone12','ACTIVE',20,10,'PC',2,1);


insert into stock_details( price, invoice_date, quantity, remaining_quantity, product_id)
values (25,'2021-01-05 00:00:00', 5 ,3 ,1);






