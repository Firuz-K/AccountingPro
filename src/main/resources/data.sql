insert into users(id, created_by, created_time, is_deleted, updated_by, updated_time, email, enabled, first_name, last_name, password, phone ,company_id, role_id)
values(1, 1, '2021-01-05 00:00:00', false, 1, '2021-01-15 00:00:00', 'admin@admin.com', true, 'Mike', 'Smith', null, '7894561231',null, null),
        (2, 2, '2021-01-06 00:00:00', false, 2, '2021-01-15 00:00:00', 'manager@admin.com', true, 'Mikey', 'Smith', null, '7894561230',null, null),
        (3, 2, '2021-01-07 00:00:00', false, 3, '2021-01-15 00:00:00', 'employee@admin.com', true, 'Micheal', 'Smith', null, '7894561235',null, null);

INSERT INTO roles(enabled, name)
values(true, 'Admin'),
      (true, 'Manager'),
      (true, 'Employee'),
      (true, 'Root');