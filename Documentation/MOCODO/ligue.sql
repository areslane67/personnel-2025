-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : ven. 31 mars 2023 à 14:29
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `m2l`
--

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `id_employe` int(255) NOT NULL,
  `nom_employe` varchar(42) DEFAULT NULL,
  `prenom_employe` varchar(42) DEFAULT NULL,
  `mail_employe` varchar(42) DEFAULT NULL,
  `mdp_employe` varchar(42) DEFAULT NULL,
  `date_arrive` date NOT NULL,
  `date_depart` date NOT NULL,
  `habilitation` int(2) NOT NULL,
  `id_ligue` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `ligue`
--

CREATE TABLE `ligue` (
  `id_ligue` int(255) NOT NULL,
  `nom_ligue` varchar(42) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`id_employe`),
  ADD KEY `id_ligue` (`id_ligue`);

--
-- Index pour la table `ligue`
--
ALTER TABLE `ligue`
  ADD PRIMARY KEY (`id_ligue`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `employe`
--
ALTER TABLE `employe`
  MODIFY `id_employe` int(255) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `ligue`
--
ALTER TABLE `ligue`
  MODIFY `id_ligue` int(255) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `employe_ibfk_1` FOREIGN KEY (`id_ligue`) REFERENCES `ligue` (`id_ligue`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
