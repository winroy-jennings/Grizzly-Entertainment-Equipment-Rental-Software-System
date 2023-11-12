-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2023 at 04:36 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `grizzlyentertainment`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `accountType` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `accountBalance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `username`, `password`, `accountType`, `firstName`, `lastName`, `accountBalance`) VALUES
(1, '1', '1', 'standard', 'John', 'Doe', 1000),
(2, 'user2', 'password2', 'premium', 'Jane', 'Smith', 2500),
(3, 'user3', 'password3', 'standard', 'Bob', 'Johnson', 1500),
(4, 'user4', 'password4', 'premium', 'Alice', 'Williams', 3000),
(5, 'user5', 'password5', 'standard', 'Charlie', 'Brown', 2000),
(6, 'user1', 'password1', 'standard', 'John', 'Doe', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `accountType` varchar(50) NOT NULL,
  `firstName` varchar(50) NOT NULL,
  `lastName` varchar(50) NOT NULL,
  `position` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `username`, `password`, `accountType`, `firstName`, `lastName`, `position`) VALUES
(1, 'emp1', 'password1', 'admin', 'John', 'Doe', 'Manager'),
(2, 'emp2', 'password2', 'standard', 'Jane', 'Smith', 'Clerk'),
(3, 'emp3', 'password3', 'standard', 'Bob', 'Johnson', 'Assistant'),
(4, 'emp4', 'password4', 'admin', 'Alice', 'Williams', 'Supervisor'),
(5, 'emp5', 'password5', 'standard', 'Charlie', 'Brown', 'Technician');

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `id` int(11) NOT NULL,
  `category` varchar(50) NOT NULL,
  `dateAvailable` date NOT NULL,
  `cost` double NOT NULL,
  `equipmentType` varchar(50) NOT NULL,
  `rentalStatus` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`id`, `category`, `dateAvailable`, `cost`, `equipmentType`, `rentalStatus`) VALUES
(1, 'CategoryA', '2023-11-15', 150, 'Staging', 1),
(2, 'CategoryB', '2023-11-16', 200, 'Lighting', 1),
(3, 'CategoryC', '2023-11-17', 100, 'Power', 0),
(4, 'CategoryD', '2023-11-18', 250, 'Sound', 0),
(5, 'CategoryE', '2023-11-19', 180, 'Staging', 1),
(6, 'CategoryF', '2023-11-20', 300, 'Lighting', 0),
(7, 'CategoryG', '2023-11-21', 120, 'Power', 1),
(8, 'CategoryH', '2023-11-22', 220, 'Sound', 0);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `customerId` int(11) NOT NULL,
  `employeeId` int(11) NOT NULL,
  `equipmentId` int(11) NOT NULL,
  `message` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `customerId`, `employeeId`, `equipmentId`, `message`) VALUES
(1, 1, 101, 201, 'Hello, I\'m interested in renting equipment for an event.'),
(2, 2, 102, 202, 'What are the available options for lighting equipment?'),
(3, 3, 103, 203, 'Do you have power generators for outdoor events?'),
(4, 4, 101, 204, 'I need sound equipment for a live performance.'),
(5, 5, 105, 205, 'Can you provide staging for a concert?');

-- --------------------------------------------------------

--
-- Table structure for table `rentalrequest`
--

CREATE TABLE `rentalrequest` (
  `id` int(11) NOT NULL,
  `requestStartDate` date NOT NULL,
  `requestEndDate` date NOT NULL,
  `customerID` int(11) NOT NULL,
  `employeeID` int(11) NOT NULL,
  `equipmentID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rentalrequest`
--

INSERT INTO `rentalrequest` (`id`, `requestStartDate`, `requestEndDate`, `customerID`, `employeeID`, `equipmentID`) VALUES
(1, '2023-11-15', '2023-11-20', 1, 101, 201),
(2, '2023-11-18', '2023-11-25', 2, 102, 202),
(3, '2023-11-22', '2023-11-30', 3, 103, 203),
(4, '2023-11-25', '2023-12-02', 4, 104, 204),
(5, '2023-11-28', '2023-12-05', 5, 105, 205),
(9, '1995-05-22', '1995-05-22', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `transactionCost` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `startDate`, `endDate`, `transactionCost`) VALUES
(3, '1995-05-22', '1995-05-22', 150);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rentalrequest`
--
ALTER TABLE `rentalrequest`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `equipment`
--
ALTER TABLE `equipment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `rentalrequest`
--
ALTER TABLE `rentalrequest`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
