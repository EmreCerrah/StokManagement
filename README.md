## DATABASE Information

### Settlements:
`kod` varchar(50) NOT NULL,
`ad` varchar(100) NOT NULL,
`tip` int NOT NULL,
`birim` varchar(10) NOT NULL,
`barkod` varchar(30) NOT NULL,
`kdv_tipi` double DEFAULT NULL,
`aciklama` text,
`olusturma_tarihi` date DEFAULT NULL,
PRIMARY KEY (`kod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

## Library

- com.lowagie.text-2.1.7
- commons-beanutils-1.9.3
- commons-collections4-4.4
- commons-digester-2.1
- commons-logging-1.2 
- jasperreports-6.20.0
- jasperreports-functions-6.20.0
- jasperreports-metadata-6.20.0
- javax.activation-1.2.0
- javax.mail
- jxl-2.6
