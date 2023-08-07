INSERT IGNORE INTO user
  (id, active,email,password)
VALUES
  (1,1, "admin", "$2a$10$/xtdFlV4Kw/seM4zRNYmouhwZ02/WbGIEcYp0GNQ.mRuBeogHuJ.u");
INSERT IGNORE INTO roles (id,role_name) VALUES(1,'ROLE_user');
INSERT IGNORE INTO roles (id,role_name) VALUES(2,'ROLE_admin');
INSERT IGNORE INTO user_role(user_id,role_id) VALUES(1,2);