USE [aha]
GO
/****** Object:  Table [dbo].[order]    Script Date: 8/15/2021 4:34:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[order](
	[orderID] [int] IDENTITY(1,1) NOT NULL,
	[cusID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[orderDetails]    Script Date: 8/15/2021 4:34:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[orderDetails](
	[orderitemID] [int] IDENTITY(1,1) NOT NULL,
	[orderID] [int] NOT NULL,
	[proID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[orderitemID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[product]    Script Date: 8/15/2021 4:34:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[product](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](100) NOT NULL,
	[price] [varchar](50) NOT NULL,
	[category] [varchar](50) NOT NULL,
	[featured] [varchar](50) NOT NULL,
	[image] [varchar](255) NOT NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[user]    Script Date: 8/15/2021 4:34:52 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[user](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NOT NULL,
	[address] [text] NOT NULL,
	[email] [varchar](50) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[product] ON 

INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (1, N'one plus 7', N'38500', N'mobiles', N'yes', N'img/oneplus-6.jpg', 1000)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (2, N'Iphone X', N'99800', N'mobiles', N'no', N'img/iphone-x.jpeg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (3, N'Pocophone F1', N'26000', N'mobiles', N'no', N'img/pocophone-f1.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (4, N'Samsung S9', N'52000', N'mobiles', N'yes', N'img/samsung-s9.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (5, N'Macbook Air', N'124000', N'laptops', N'yes', N'img/macbook-air.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (6, N'Asus Rog', N'92000', N'laptops', N'no', N'img/asus-rog.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (7, N'HP Pavilion', N'62000', N'laptops', N'no', N'img/hp-pavilion.png', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (8, N'Acer Predator', N'76000', N'laptops', N'yes', N'img/acer-predator.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (9, N'Jeans', N'750', N'clothing', N'no', N'img/jeans.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (10, N'Shirts', N'600', N'clothing', N'yes', N'img/shirts.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (11, N'T-Shirt', N'400', N'clothing', N'no', N'img/t-shirt.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (12, N'Sweatshirt', N'550', N'clothing', N'no', N'img/sweatshirt.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (13, N'Painting', N'1200', N'home decor', N'no', N'img/painting.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (14, N'Wall Shelf', N'24000', N'home decor', N'yes', N'img/wall-shelf.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (15, N'Wall Clock', N'450', N'home decor', N'no', N'img/clock.jpg', 9999)
INSERT [dbo].[product] ([id], [name], [price], [category], [featured], [image], [quantity]) VALUES (20, N'asdasd', N'132132132', N'laptops', N'yes', N'img/asdasd', 99999)
SET IDENTITY_INSERT [dbo].[product] OFF
SET IDENTITY_INSERT [dbo].[user] ON 

INSERT [dbo].[user] ([id], [name], [address], [email], [username], [password]) VALUES (1, N'thanh', N'asd', N'123@asd', N'123', N'123')
INSERT [dbo].[user] ([id], [name], [address], [email], [username], [password]) VALUES (2, N'ta', N'asdasd', N'123@123', N'234', N'123')
INSERT [dbo].[user] ([id], [name], [address], [email], [username], [password]) VALUES (3, N'thanh', N'asd', N'thanh@123', N'456', N'123')
SET IDENTITY_INSERT [dbo].[user] OFF
ALTER TABLE [dbo].[order]  WITH CHECK ADD FOREIGN KEY([cusID])
REFERENCES [dbo].[user] ([id])
GO
ALTER TABLE [dbo].[orderDetails]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[order] ([orderID])
GO
ALTER TABLE [dbo].[orderDetails]  WITH CHECK ADD FOREIGN KEY([proID])
REFERENCES [dbo].[product] ([id])
GO
