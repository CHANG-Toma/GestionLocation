-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 13 fév. 2023 à 11:26
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `appart`
--

-- --------------------------------------------------------

--
-- Structure de la table `appartements`
--

DROP TABLE IF EXISTS `appartements`;
CREATE TABLE IF NOT EXISTS `appartements` (
  `numappart` int(11) NOT NULL,
  `rue` int(11) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `CP` int(11) DEFAULT NULL,
  `etage` int(11) DEFAULT NULL,
  `prix_log` decimal(15,2) DEFAULT NULL,
  `prix_charg` decimal(15,2) DEFAULT NULL,
  `ascenseur` tinyint(1) NOT NULL,
  `preavis` tinyint(1) NOT NULL,
  `date_libre` date DEFAULT NULL,
  `id_typeAppart` int(11) NOT NULL,
  `arrondissement_dem` varchar(50) NOT NULL,
  PRIMARY KEY (`numappart`),
  KEY `id_typeAppart` (`id_typeAppart`),
  KEY `arrondissement_dem` (`arrondissement_dem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `appartements`
--

INSERT INTO `appartements` (`numappart`, `rue`, `ville`, `CP`, `etage`, `prix_log`, `prix_charg`, `ascenseur`, `preavis`, `date_libre`, `id_typeAppart`, `arrondissement_dem`) VALUES
(1, 123, 'Paris', 75000, 4, '500.00', '550.00', 1, 1, '2022-01-01', 1, '1'),
(2, 456, 'Lyon', 69000, 2, '600.00', '650.00', 0, 0, '2022-02-01', 2, '2'),
(3, 789, 'Marseille', 13000, 1, '700.00', '750.00', 1, 0, '2022-03-01', 3, '3'),
(4, 246, 'Lille', 59000, 5, '400.00', '450.00', 0, 1, '2022-04-01', 4, '4'),
(5, 369, 'Toulouse', 31000, 3, '800.00', '850.00', 1, 0, '2022-05-01', 5, '5'),
(6, 159, 'Bordeaux', 33000, 4, '450.00', '500.00', 0, 1, '2022-06-01', 1, '6'),
(7, 753, 'Strasbourg', 67000, 2, '550.00', '600.00', 1, 0, '2022-07-01', 2, '7'),
(8, 951, 'Nantes', 44000, 1, '650.00', '700.00', 0, 1, '2022-08-01', 3, '8'),
(9, 852, 'Nice', 6000, 5, '500.00', '550.00', 1, 0, '2022-09-01', 4, '9'),
(10, 741, 'Rennes', 35000, 3, '600.00', '650.00', 0, 1, '2022-10-01', 5, '10');

-- --------------------------------------------------------

--
-- Structure de la table `arrondissement`
--

DROP TABLE IF EXISTS `arrondissement`;
CREATE TABLE IF NOT EXISTS `arrondissement` (
  `arrondissement_dem` varchar(50) NOT NULL,
  `libelle` varchar(100) NOT NULL,
  PRIMARY KEY (`arrondissement_dem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `arrondissement`
--

INSERT INTO `arrondissement` (`arrondissement_dem`, `libelle`) VALUES
('1', '1er Arrondissement'),
('10', '10ème Arrondissement'),
('2', '2ème Arrondissement'),
('3', '3ème Arrondissement'),
('4', '4ème Arrondissement'),
('5', '5ème Arrondissement'),
('6', '6ème Arrondissement'),
('7', '7ème Arrondissement'),
('8', '8ème Arrondissement'),
('9', '9ème Arrondissement');

-- --------------------------------------------------------

--
-- Structure de la table `avoir`
--

DROP TABLE IF EXISTS `avoir`;
CREATE TABLE IF NOT EXISTS `avoir` (
  `num_dem` int(11) NOT NULL,
  `id_typeAppart` int(11) NOT NULL,
  PRIMARY KEY (`num_dem`,`id_typeAppart`),
  KEY `id_typeAppart` (`id_typeAppart`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `avoir`
--

INSERT INTO `avoir` (`num_dem`, `id_typeAppart`) VALUES
(4, 1),
(8, 1),
(2, 2),
(6, 2),
(10, 2),
(1, 3),
(7, 3),
(3, 4),
(9, 4),
(5, 5);

-- --------------------------------------------------------

--
-- Structure de la table `banque`
--

DROP TABLE IF EXISTS `banque`;
CREATE TABLE IF NOT EXISTS `banque` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `ville` varchar(50) DEFAULT NULL,
  `CP` varchar(50) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `banque`
--

INSERT INTO `banque` (`id`, `nom`, `adresse`, `ville`, `CP`, `tel`) VALUES
(1, 'Banque de France', '1, place de la bourse', 'Paris', '75002', 123456789),
(2, 'Crédit Agricole', '2, rue des alpes', 'Lyon', '69001', 123456788),
(3, 'BNP Paribas', '3, boulevard des docks', 'Marseille', '13005', 123456787),
(4, 'Société Générale', '4, avenue du nord', 'Lille', '59000', 123456786),
(5, 'LCL', '5, rue du midi', 'Toulouse', '31000', 123456785),
(6, 'La Banque Postale', '6, avenue du sud', 'Bordeaux', '33000', 123456784),
(7, 'HSBC', '10, rue de la mer', 'Rennes', '35000', 123456780);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `CP` int(11) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `nom`, `Prenom`, `adresse`, `Ville`, `CP`, `tel`) VALUES
(1, 'Dupont', 'Jean', '1, rue de la paix', 'Paris', 75000, 123456789),
(2, 'Durand', 'Marie', '2, avenue des champs', 'Lyon', 69000, 123456788),
(3, 'Martin', 'Luc', '3, boulevard de la mer', 'Marseille', 13000, 123456787),
(4, 'Petit', 'Anne', '4, rue du marché', 'Lille', 59000, 123456786),
(5, 'Lefebvre', 'Paul', '5, avenue du soleil', 'Toulouse', 31000, 123456785),
(6, 'Roux', 'Claire', '6, rue des étoiles', 'Bordeaux', 33000, 123456784),
(7, 'Garcia', 'David', '7, avenue des fleurs', 'Strasbourg', 67000, 123456783),
(8, 'Robert', 'Emilie', '8, boulevard de la plage', 'Nantes', 44000, 123456782),
(9, 'Smith', 'William', '9, rue de la montagne', 'Nice', 6000, 123456781),
(10, 'Johnson', 'John', '10, avenue des arbres', 'Rennes', 35000, 123456780);

-- --------------------------------------------------------

--
-- Structure de la table `concerner`
--

DROP TABLE IF EXISTS `concerner`;
CREATE TABLE IF NOT EXISTS `concerner` (
  `num_dem` int(11) NOT NULL,
  `arrondissement_num` varchar(50) NOT NULL,
  PRIMARY KEY (`arrondissement_num`,`num_dem`),
  KEY `num_dem` (`num_dem`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `concerner`
--

INSERT INTO `concerner` (`num_dem`, `arrondissement_num`) VALUES
(1, '2'),
(2, '4'),
(3, '7'),
(4, '5'),
(5, '9'),
(6, '3'),
(7, '6'),
(8, '10'),
(9, '1'),
(10, '8');

-- --------------------------------------------------------

--
-- Structure de la table `demandes`
--

DROP TABLE IF EXISTS `demandes`;
CREATE TABLE IF NOT EXISTS `demandes` (
  `num_dem` int(11) NOT NULL,
  `num_cli` int(11) DEFAULT NULL,
  `date_limite` date DEFAULT NULL,
  `prix_min` int(11) DEFAULT NULL,
  `prix_max` int(11) DEFAULT NULL,
  PRIMARY KEY (`num_dem`),
  KEY `num_cli` (`num_cli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `demandes`
--

INSERT INTO `demandes` (`num_dem`, `num_cli`, `date_limite`, `prix_min`, `prix_max`) VALUES
(1, 2, '2022-05-01', 400, 700),
(2, 4, '2022-06-01', 450, 950),
(3, 7, '2022-07-01', 550, 600),
(4, 5, '2022-08-01', 800, 1200),
(5, 9, '2022-09-01', 500, 900),
(6, 3, '2022-10-01', 700, 750),
(7, 6, '2022-11-01', 450, 500),
(8, 10, '2022-12-01', 650, 1000),
(9, 1, '2023-01-01', 500, 700),
(10, 8, '2023-02-01', 600, 950);

-- --------------------------------------------------------

--
-- Structure de la table `locataires`
--

DROP TABLE IF EXISTS `locataires`;
CREATE TABLE IF NOT EXISTS `locataires` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `dateNaissance` varchar(50) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `RIB` int(50) DEFAULT NULL,
  `idBanque` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idBanque` (`idBanque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `locataires`
--

INSERT INTO `locataires` (`id`, `nom`, `prenom`, `dateNaissance`, `tel`, `RIB`, `idBanque`) VALUES
(1, 'Dupont', 'Jean', '2000-01-01', 123456789, 123456789, 1),
(2, 'Martin', 'Paul', '1999-02-02', 123456788, 123467890, 2),
(3, 'Dubois', 'Marie', '1998-03-03', 123456787, 124567802, 3),
(4, 'Roche', 'Jacques', '1997-04-04', 123456786, 234568903, 4),
(5, 'Leroy', 'Claire', '1996-05-05', 123456785, 134568904, 5),
(6, 'Moreau', 'David', '1995-06-06', 123456784, 124578905, 6),
(7, 'Girard', 'Sophie', '1994-07-07', 123456783, 125678906, 7),
(8, 'Simon', 'Laure', '1993-08-08', 123456782, 124678907, 4),
(9, 'Lambert', 'Julie', '1992-09-09', 123456781, 345678918, 6),
(10, 'Faure', 'Eric', '1991-10-10', 123456780, 145678909, 2);

-- --------------------------------------------------------

--
-- Structure de la table `occuper`
--

DROP TABLE IF EXISTS `occuper`;
CREATE TABLE IF NOT EXISTS `occuper` (
  `numappart` int(11) NOT NULL,
  `id_locataire` int(11) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`numappart`,`id_locataire`),
  KEY `id` (`id_locataire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `occuper`
--

INSERT INTO `occuper` (`numappart`, `id_locataire`, `date_debut`, `date_fin`) VALUES
(1, 1, '2022-01-15', '2022-01-31'),
(2, 2, '2022-02-10', '2022-02-28'),
(3, 3, '2022-03-01', '2022-03-15'),
(4, 4, '2022-04-01', '2022-04-20'),
(5, 5, '2022-05-10', '2022-05-31'),
(6, 1, '2022-06-01', '2022-06-15'),
(7, 2, '2022-07-01', '2022-07-31'),
(8, 3, '2022-08-01', '2022-08-15'),
(9, 4, '2022-09-01', '2022-09-30'),
(10, 5, '2022-10-01', '2022-10-31');

-- --------------------------------------------------------

--
-- Structure de la table `posseder`
--

DROP TABLE IF EXISTS `posseder`;
CREATE TABLE IF NOT EXISTS `posseder` (
  `numappart` int(11) NOT NULL,
  `id_proprietaire` int(11) NOT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`numappart`,`id_proprietaire`),
  KEY `id` (`id_proprietaire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `posseder`
--

INSERT INTO `posseder` (`numappart`, `id_proprietaire`, `date_debut`, `date_fin`) VALUES
(1, 10, '2022-01-01', '2022-02-01'),
(2, 5, '2022-03-01', '2022-04-01'),
(3, 7, '2022-05-01', '2022-06-01'),
(4, 9, '2022-07-01', '2022-08-01'),
(5, 2, '2022-09-01', '2022-10-01'),
(6, 8, '2022-11-01', '2022-12-01'),
(7, 3, '2022-12-01', '2023-01-01'),
(8, 6, '2023-01-01', '2023-02-01'),
(9, 4, '2023-02-01', '2023-03-01'),
(10, 1, '2023-03-01', '2023-04-01');

-- --------------------------------------------------------

--
-- Structure de la table `proprietaires`
--

DROP TABLE IF EXISTS `proprietaires`;
CREATE TABLE IF NOT EXISTS `proprietaires` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `Prenom` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `Ville` varchar(50) DEFAULT NULL,
  `CP` int(11) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `proprietaires`
--

INSERT INTO `proprietaires` (`id`, `nom`, `Prenom`, `adresse`, `Ville`, `CP`, `tel`) VALUES
(1, 'Dupont', 'Jacques', '1 rue de la Paix', 'Paris', 75000, 600000165),
(2, 'Martin', 'Marie', '2 avenue des Champs Elysées', 'Paris', 75016, 696800000),
(3, 'Leroy', 'Luc', '3 boulevard de la Villette', 'Lyon', 69003, 600001650),
(4, 'Rousseau', 'Emilie', '4 rue de la Soif', 'Lyon', 69006, 600001340),
(5, 'Dubois', 'David', '5 avenue des Ternes', 'Paris', 75017, 600016800),
(6, 'Moreau', 'Laure', '6 rue de Belleville', 'Paris', 75019, 600067400),
(7, 'Garcia', 'Pascal', '7 rue de la Roquette', 'Paris', 75011, 606450000),
(8, 'Petit', 'Catherine', '8 avenue des Gobelins', 'Paris', 75013, 601650000),
(9, 'Fournier', 'Véronique', '9 rue de Belleville', 'Paris', 75019, 648200000),
(10, 'Roux', 'Anne', '10 avenue des Gobelins', 'Paris', 75013, 600006940),
(11, 'Girard', 'Patrick', '11 rue de la Roquette', 'Paris', 75011, 603450000),
(12, 'Lefebvre', 'Sylvie', '12 rue de Belleville', 'Paris', 75019, 606480000);

-- --------------------------------------------------------

--
-- Structure de la table `type_appart`
--

DROP TABLE IF EXISTS `type_appart`;
CREATE TABLE IF NOT EXISTS `type_appart` (
  `id_typeAppart` int(11) NOT NULL,
  `type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_typeAppart`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `type_appart`
--

INSERT INTO `type_appart` (`id_typeAppart`, `type`) VALUES
(1, 'Studio'),
(2, 'Appartement 1 chambre'),
(3, 'Appartement 2 chambres'),
(4, 'Appartement 3 chambres'),
(5, 'Maison');

-- --------------------------------------------------------

--
-- Structure de la table `visiter`
--

DROP TABLE IF EXISTS `visiter`;
CREATE TABLE IF NOT EXISTS `visiter` (
  `numappart` int(11) NOT NULL,
  `idClient` int(11) NOT NULL,
  `date_visite` date DEFAULT NULL,
  PRIMARY KEY (`numappart`,`idClient`),
  KEY `id` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `visiter`
--

INSERT INTO `visiter` (`numappart`, `idClient`, `date_visite`) VALUES
(1, 10, '2022-11-01'),
(2, 9, '2022-12-01'),
(3, 8, '2023-01-01'),
(4, 7, '2023-02-01'),
(5, 6, '2023-03-01'),
(6, 5, '2023-04-01'),
(7, 4, '2023-05-01'),
(8, 3, '2023-06-01'),
(9, 2, '2023-07-01'),
(10, 1, '2023-08-01');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `appartements`
--
ALTER TABLE `appartements`
  ADD CONSTRAINT `appartements_ibfk_1` FOREIGN KEY (`id_typeAppart`) REFERENCES `type_appart` (`id_typeAppart`),
  ADD CONSTRAINT `appartements_ibfk_2` FOREIGN KEY (`arrondissement_dem`) REFERENCES `arrondissement` (`arrondissement_dem`);

--
-- Contraintes pour la table `avoir`
--
ALTER TABLE `avoir`
  ADD CONSTRAINT `avoir_ibfk_1` FOREIGN KEY (`num_dem`) REFERENCES `demandes` (`num_dem`),
  ADD CONSTRAINT `avoir_ibfk_2` FOREIGN KEY (`id_typeAppart`) REFERENCES `type_appart` (`id_typeAppart`);

--
-- Contraintes pour la table `concerner`
--
ALTER TABLE `concerner`
  ADD CONSTRAINT `concerner_ibfk_1` FOREIGN KEY (`arrondissement_num`) REFERENCES `arrondissement` (`arrondissement_dem`),
  ADD CONSTRAINT `concerner_ibfk_2` FOREIGN KEY (`num_dem`) REFERENCES `demandes` (`num_dem`);

--
-- Contraintes pour la table `demandes`
--
ALTER TABLE `demandes`
  ADD CONSTRAINT `demandes_ibfk_1` FOREIGN KEY (`num_cli`) REFERENCES `clients` (`id`);

--
-- Contraintes pour la table `locataires`
--
ALTER TABLE `locataires`
  ADD CONSTRAINT `locataires_ibfk_1` FOREIGN KEY (`idBanque`) REFERENCES `banque` (`id`);

--
-- Contraintes pour la table `occuper`
--
ALTER TABLE `occuper`
  ADD CONSTRAINT `occuper_ibfk_1` FOREIGN KEY (`numappart`) REFERENCES `appartements` (`numappart`),
  ADD CONSTRAINT `occuper_ibfk_2` FOREIGN KEY (`id_locataire`) REFERENCES `locataires` (`id`);

--
-- Contraintes pour la table `posseder`
--
ALTER TABLE `posseder`
  ADD CONSTRAINT `posseder_ibfk_1` FOREIGN KEY (`numappart`) REFERENCES `appartements` (`numappart`),
  ADD CONSTRAINT `posseder_ibfk_2` FOREIGN KEY (`id_proprietaire`) REFERENCES `proprietaires` (`id`);

--
-- Contraintes pour la table `visiter`
--
ALTER TABLE `visiter`
  ADD CONSTRAINT `visiter_ibfk_1` FOREIGN KEY (`numappart`) REFERENCES `appartements` (`numappart`),
  ADD CONSTRAINT `visiter_ibfk_2` FOREIGN KEY (`idClient`) REFERENCES `clients` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
