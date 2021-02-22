-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 22 fév. 2021 à 15:56
-- Version du serveur :  10.4.14-MariaDB
-- Version de PHP : 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gest_parc`
--

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `Id_categorie` int(11) NOT NULL,
  `libelle` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`Id_categorie`, `libelle`) VALUES
(1, 'camion'),
(2, 'utilitaire'),
(3, 'moto'),
(4, 'voiture'),
(5, 'camping-car'),
(6, 'scooter');

-- --------------------------------------------------------

--
-- Structure de la table `controle_tech`
--

CREATE TABLE `controle_tech` (
  `Id_ct` int(11) NOT NULL,
  `date_debut_ct` date NOT NULL,
  `date_fin_ct` date NOT NULL,
  `cout_ct` double NOT NULL,
  `Id_vehicule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `controle_tech`
--

INSERT INTO `controle_tech` (`Id_ct`, `date_debut_ct`, `date_fin_ct`, `cout_ct`, `Id_vehicule`) VALUES
(1, '2019-12-25', '2021-12-25', 150, 1),
(2, '2020-03-25', '2022-03-25', 150, 2),
(3, '2019-12-05', '2021-12-05', 150, 3),
(4, '2019-11-15', '2021-11-15', 150, 4),
(5, '2020-10-12', '2022-10-12', 150, 5),
(6, '2021-02-08', '2023-02-08', 150, 6),
(7, '2020-01-25', '2022-01-25', 150, 7),
(8, '2021-03-18', '2023-03-18', 150, 8);

-- --------------------------------------------------------

--
-- Structure de la table `reparation`
--

CREATE TABLE `reparation` (
  `Id_reparation` int(11) NOT NULL,
  `date_reparation` date NOT NULL,
  `descriptif` varchar(255) NOT NULL,
  `cout` double NOT NULL,
  `Id_vehicule` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reparation`
--

INSERT INTO `reparation` (`Id_reparation`, `date_reparation`, `descriptif`, `cout`, `Id_vehicule`) VALUES
(1, '2015-12-26', 'remplacement pot d\'echappement', 223, 1),
(2, '2015-11-26', 'remplacement courroie distribution', 850, 1),
(3, '2018-01-12', 'remplacement demarreur', 150, 2),
(4, '2017-02-05', 'remplacement bougies préchauffage', 250, 4),
(5, '2017-02-15', 'remplacement plaquettes', 350, 4),
(6, '2019-09-05', 'remplacement radiateur', 200, 5),
(7, '2019-09-15', 'remplacement pare choc', 150, 5),
(8, '2019-09-28', 'remplacement alternateur', 180, 5),
(9, '2019-10-12', 'remplacement injecteur', 250, 6),
(10, '2017-03-04', 'remplacement 2 phares avant', 550, 7);

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

CREATE TABLE `vehicule` (
  `Id_vehicule` int(11) NOT NULL,
  `constructeur` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `annee_model` smallint(6) DEFAULT NULL,
  `puissance` double NOT NULL,
  `nb_km` int(11) NOT NULL,
  `prix` double NOT NULL,
  `Id_categorie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`Id_vehicule`, `constructeur`, `model`, `annee_model`, `puissance`, `nb_km`, `prix`, `Id_categorie`) VALUES
(1, 'toyota', 'yaris1', 2005, 75, 85000, 2750, 4),
(2, 'toyota', 'yaris2', 2015, 85, 68000, 3750, 4),
(3, 'renault', 'twingo1', 2000, 70, 26000, 1500, 4),
(4, 'renault', 'clio3', 2014, 125, 75000, 4550, 4),
(5, 'seat', 'leon', 2017, 180, 90000, 5600, 4),
(6, 'seat', 'arona', 2018, 200, 85557, 8750, 4),
(7, 'bmw', 'serie7', 2016, 250, 90658, 6050, 4),
(8, 'smart', 'fortwo', 2012, 60, 89000, 2500, 4),
(9, 'yamaha', 'yzf-r1', 2014, 150, 50000, 4700, 3),
(10, 'yamaha', 'tracer9', 2018, 220, 87500, 8750, 3),
(11, 'kawasaki', 'z650', 2015, 160, 50500, 5000, 3),
(12, 'bmw', 'hp4-race', 2008, 180, 978052, 9750, 3),
(13, 'Suzuki', 'gsx-rr', 2012, 200, 87502, 8750, 3),
(14, 'citroen', 'jumpy', 2011, 125, 68506, 6800, 2),
(15, 'dacia', 'logan', 2011, 60, 100562, 3000, 4);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`Id_categorie`);

--
-- Index pour la table `controle_tech`
--
ALTER TABLE `controle_tech`
  ADD PRIMARY KEY (`Id_ct`),
  ADD KEY `Id_vehicule` (`Id_vehicule`);

--
-- Index pour la table `reparation`
--
ALTER TABLE `reparation`
  ADD PRIMARY KEY (`Id_reparation`),
  ADD KEY `Id_vehicule` (`Id_vehicule`);

--
-- Index pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD PRIMARY KEY (`Id_vehicule`),
  ADD KEY `Id_categorie` (`Id_categorie`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `Id_categorie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `controle_tech`
--
ALTER TABLE `controle_tech`
  MODIFY `Id_ct` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `reparation`
--
ALTER TABLE `reparation`
  MODIFY `Id_reparation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `vehicule`
--
ALTER TABLE `vehicule`
  MODIFY `Id_vehicule` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `controle_tech`
--
ALTER TABLE `controle_tech`
  ADD CONSTRAINT `controle_tech_ibfk_1` FOREIGN KEY (`Id_vehicule`) REFERENCES `vehicule` (`Id_vehicule`);

--
-- Contraintes pour la table `reparation`
--
ALTER TABLE `reparation`
  ADD CONSTRAINT `reparation_ibfk_1` FOREIGN KEY (`Id_vehicule`) REFERENCES `vehicule` (`Id_vehicule`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`Id_categorie`) REFERENCES `categorie` (`Id_categorie`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
