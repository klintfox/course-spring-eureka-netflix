Insert Into usuarios (username, password, enabled, nombre, apellido, email) Values('klint','$2a$10$rt1dWncBgUgsIOsrECnfFeos3EvdPzcqQ38tvP6vhxmvCEmBwPSwG',true,'klint','roman','klint@gmail.com');
Insert Into usuarios (username, password, enabled, nombre, apellido, email) Values('feli','$2a$10$KuIShXecQo5ZbKgCZY0LWeQviMEsCkmQFVqdnkWtduonlN13ASedG',true,'feli','roman','feli@gmail.com');

Insert Into roles (nombre) values ('ROLE_ADMIN');
Insert Into roles (nombre) values ('ROLE_USER');

Insert Into usuarios_roles (usuario_id, role_id) Values (1, 1);
Insert Into usuarios_roles (usuario_id, role_id) Values (1, 2);
Insert Into usuarios_roles (usuario_id, role_id) Values (2, 1);