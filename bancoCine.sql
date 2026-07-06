CREATE DATABASE `cineproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `analise` (
  `filme_id` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `nota` int DEFAULT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbkqd00p8kimvs78t4emy9ii12` (`filme_id`),
  CONSTRAINT `FKbkqd00p8kimvs78t4emy9ii12` FOREIGN KEY (`filme_id`) REFERENCES `filme` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `filme` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ano` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `sinopse` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

