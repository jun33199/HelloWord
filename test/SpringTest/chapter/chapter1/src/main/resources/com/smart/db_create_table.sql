CREATE TABLE IF NOT EXISTS t_user (
  user_id       INT NOT NULL AUTO_INCREMENT,
  user_name     VARCHAR(191) NOT NULL,
  password      VARCHAR(191) NOT NULL,
  last_visit    datetime,
  last_ip       VARCHAR(191),
  PRIMARY KEY (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_bin;

CREATE TABLE IF NOT EXISTS t_login_log (
  login_log_id    INT NOT NULL AUTO_INCREMENT,
  user_id         INT NOT NULL,
  login_datetime  datetime NOT NULL,
  ip              VARCHAR(191) NOT NULL,
  PRIMARY KEY (login_log_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_bin;