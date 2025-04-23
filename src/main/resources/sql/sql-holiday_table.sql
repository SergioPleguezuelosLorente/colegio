CREATE TABLE IF NOT EXISTS `holidays` (
  `day` varchar(20) NOT NULL,
  `reason` varchar(100) NOT NULL,
  `type` varchar(20) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `updated_at` TIMESTAMP DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL
);


INSERT INTO `holidays` (`day`,`reason`,`type`,`created_at`,`created_by`) VALUES
    ('Jan 1', 'New Year', 'Festival', now(), 'Anonymous'),
    ('Oct 31', 'Halloween', 'Festival', now(), 'Anonymous'),
    ('Nov 24', 'Thanksgivind Day', 'Festival', now(), 'Anonymous'),
    ('Dec 25', 'Christmas', 'Festival', now(), 'Anonymous'),
    ('Jan 17', 'Martin Luther King Day', 'Federal', now(), 'Anonymous'),
    ('July 3', 'Independence Day', 'Federal', now(), 'Anonymous'),
    ('Sep 5', 'Labor day', 'Federal', now(), 'Anonymous'),
    ('Nov 11', 'Veterans Day', 'Federal', now(), 'Anonymous');

