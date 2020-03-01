CREATE TABLE User (
    Id                  int(11) NOT NULL AUTO_INCREMENT,
    Username            varchar(255) NOT NULL UNIQUE,
    Email               varchar(255),
    Password            varchar(255) NOT NULL,
    RequestsPerDayQuota int(11) NOT NULL,
    Period              timestamp(6) NOT NULL,
    RemainingRequests   int(11) NOT NULL,
    Token               int(11) UNIQUE,
    CONSTRAINT PK_User
      PRIMARY KEY (Id));
CREATE UNIQUE INDEX IX_User_Username
  ON User (Username);
