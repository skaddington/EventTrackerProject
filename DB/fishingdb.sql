-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fishingdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fishingdb` ;

-- -----------------------------------------------------
-- Schema fishingdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fishingdb` DEFAULT CHARACTER SET utf8 ;
USE `fishingdb` ;

-- -----------------------------------------------------
-- Table `fish`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fish` ;

CREATE TABLE IF NOT EXISTS `fish` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `common_name` VARCHAR(45) NOT NULL,
  `scientific_name` VARCHAR(45) NULL,
  `image_url` VARCHAR(2000) NULL,
  `website_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `common_name_UNIQUE` (`common_name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `body_of_water`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `body_of_water` ;

CREATE TABLE IF NOT EXISTS `body_of_water` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `county` VARCHAR(45) NULL,
  `type` TINYINT NOT NULL,
  `elevation_ft` INT NOT NULL,
  `description` VARCHAR(1000) NULL,
  `image_url` VARCHAR(2000) NULL,
  `website_url` VARCHAR(2000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
ROW_FORMAT = Default;


-- -----------------------------------------------------
-- Table `time_of_day`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_of_day` ;

CREATE TABLE IF NOT EXISTS `time_of_day` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `timeframe` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `image_url` VARCHAR(2000) NULL DEFAULT 'https://rlv.zcache.com/pussyfoot_fishbowl_fun_poster-r8aa9bad5e4fd4f9e8f2b55ab8cadbb47_jbqw_8byvr_736.webp',
  `enabled` TINYINT NULL DEFAULT 1,
  `role` VARCHAR(45) NULL DEFAULT 'USER',
  `created_at` DATETIME NULL,
  `last_update` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `catch_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `catch_log` ;

CREATE TABLE IF NOT EXISTS `catch_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `weight_lbs` DECIMAL(4,2) NOT NULL,
  `length_inch` DECIMAL(4,2) NOT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  `created_at` DATETIME NULL,
  `last_update` DATETIME NULL,
  `fish_id` INT NOT NULL,
  `body_of_water_id` INT NOT NULL,
  `time_of_day_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`),
  INDEX `fk_catch_log_fish1_idx` (`fish_id` ASC),
  INDEX `fk_catch_log_body_of_water1_idx` (`body_of_water_id` ASC),
  INDEX `fk_catch_log_time_of_day1_idx` (`time_of_day_id` ASC),
  INDEX `fk_catch_log_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_catch_log_fish1`
    FOREIGN KEY (`fish_id`)
    REFERENCES `fish` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_catch_log_body_of_water1`
    FOREIGN KEY (`body_of_water_id`)
    REFERENCES `body_of_water` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_catch_log_time_of_day1`
    FOREIGN KEY (`time_of_day_id`)
    REFERENCES `time_of_day` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_catch_log_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `available_fish`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `available_fish` ;

CREATE TABLE IF NOT EXISTS `available_fish` (
  `body_of_water_id` INT NOT NULL,
  `fish_id` INT NOT NULL,
  PRIMARY KEY (`body_of_water_id`, `fish_id`),
  INDEX `fk_body_of_water_has_fish_fish1_idx` (`fish_id` ASC),
  INDEX `fk_body_of_water_has_fish_body_of_water1_idx` (`body_of_water_id` ASC),
  CONSTRAINT `fk_body_of_water_has_fish_body_of_water1`
    FOREIGN KEY (`body_of_water_id`)
    REFERENCES `body_of_water` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_body_of_water_has_fish_fish1`
    FOREIGN KEY (`fish_id`)
    REFERENCES `fish` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS angler@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'angler'@'localhost' IDENTIFIED BY 'angler';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'angler'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `fish`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishingdb`;
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (1, 'Greenback Cutthroat Trout', 'Oncorhynchus clarkii stomias', 'https://westernnativetrout.org/wp-content/uploads/2019/07/greenback-cutthroat.jpg', 'https://en.wikipedia.org/wiki/Greenback_cutthroat_trout');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (2, 'Kokanee Salmon', 'Oncorhynchus nerka', 'https://www.seafoodwatch.org/globalassets/sfw-data-blocks/species/salmon/sockeye-salmon.png', 'https://en.wikipedia.org/wiki/Kokanee_salmon');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (3, 'Rainbow Trout', 'Oncorhynchus mykiss', 'https://westernnativetrout.org/wp-content/uploads/2019/07/alaskan_rainbow_lg.jpg', 'https://en.wikipedia.org/wiki/Rainbow_trout');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (4, 'Tiger Muskie', 'Esox masquinongy × lucius', 'https://upload.wikimedia.org/wikipedia/commons/5/59/Tiger_muskellunge_%28Duane_Raver%29.png', 'https://en.wikipedia.org/wiki/Tiger_muskellunge');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (5, 'Bluegill', 'Lepomis macrochirus', 'https://outdoornebraska.gov/wp-content/uploads/2023/03/Tomelleri_bluegill_1440x643.jpg', 'https://en.wikipedia.org/wiki/Bluegill');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (6, 'Northern Pike', 'Esox lucius', 'https://slipperywinds.com/wp-content/uploads/2022/11/Pike-clipart.png', 'https://en.wikipedia.org/wiki/Northern_pike');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (7, 'Mountain Whitefish', 'Prosopium williamsoni', 'https://www.maine.gov/ifw/images/lakewhitefish.jpg', 'https://en.wikipedia.org/wiki/Mountain_whitefish');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (8, 'Common Carp', 'Cyprinus carpio', 'https://www.nps.gov/miss/learn/nature/images/common_carp.jpg', 'https://en.wikipedia.org/wiki/Eurasian_carp');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (9, 'Lake Trout', 'Salvelinus namaycush', 'https://cdn.britannica.com/55/37355-050-05F6AC1E/Lake-trout.jpg', 'https://en.wikipedia.org/wiki/Lake_trout');
INSERT INTO `fish` (`id`, `common_name`, `scientific_name`, `image_url`, `website_url`) VALUES (10, 'Tiger Trout', 'Salmo trutta × Salvelinus fontinalis', 'https://www.eregulations.com/assets/images/books/wyfw/22wyfw/Tiger_Trout.jpg', 'https://en.wikipedia.org/wiki/Tiger_trout');

COMMIT;


-- -----------------------------------------------------
-- Data for table `body_of_water`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishingdb`;
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (1, 'Standley Lake', 'Jefferson', 0, 5509, 'A 1,200-acre reservoir located in Westminster, Colorado. While originally constructed to provide water for the agricultural irrigation of the surrounding region northwest of Denver, it now serves primarily as the municipal water supply for the cities of Westminster, Northglenn, and Thornton. The lake is also used for recreation, and is the prominent feature of the surrounding 3,000-acre Standley Lake Regional Park.', 'https://www.cityofwestminster.us/Portals/1/Images/MainPage1900x545.jpg?ver=2017-08-10-205135-127', 'https://en.wikipedia.org/wiki/Standley_Lake');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (2, 'Lake Estes', 'Larimer', 0, 7470, 'A reservoir in Estes Park, Colorado created by Olympus Dam. The lake has a shoreline of about 4 miles and a surface area of 185 acres. The reservoir lies on the Big Thompson River and is a component of the Colorado-Big Thompson Project.', 'https://images.alltrails.com/eyJidWNrZXQiOiJhc3NldHMuYWxsdHJhaWxzLmNvbSIsImtleSI6InVwbG9hZHMvcGhvdG8vaW1hZ2UvMTE1OTQ0MDYvMDk2YjA3Y2NmYjA4MmEyMGFiYzljYmJjMzI1ZDRmZTMuanBnIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJqcGVnIiwicmVzaXplIjp7IndpZHRoIjoxMDIyLCJoZWlnaHQiOjMxMiwiZml0IjoiY292ZXIifSwicm90YXRlIjpudWxsLCJqcGVnIjp7InRyZWxsaXNRdWFudGlzYXRpb24iOnRydWUsIm92ZXJzaG9vdERlcmluZ2luZyI6dHJ1ZSwib3B0aW1pc2VTY2FucyI6dHJ1ZSwicXVhbnRpc2F0aW9uVGFibGUiOjN9fX0=', 'https://en.wikipedia.org/wiki/Lake_Estes');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (3, 'Gross Reservoir', 'Boulder', 0, 7290, 'Completed in 1954, the reservoir has a surface area of 440 acres, and the spillway sits at 7,225 feet elevation. The reservoir receives water from the western side of the Continental Divide through the Moffat Tunnel. South Boulder Creek flows out of the 340 foot-high dam.', 'https://psl.noaa.gov/news/2021/img/wikimedia_commons-Gross_Reservoir.png', 'https://en.wikipedia.org/wiki/Gross_Reservoir');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (4, 'Elevenmile Reservoir', 'Park', 0, 8565, 'Completed in 1932 after two years of construction at a cost of only $1.5 million, Eleven Mile stands 135 feet above the South Platte riverbed. The 6-mile-long reservoir is second largest in Denver Water\'s system and one of the largest bodies of water east of the Continental Divide.', 'https://images.alltrails.com/eyJidWNrZXQiOiJhc3NldHMuYWxsdHJhaWxzLmNvbSIsImtleSI6InVwbG9hZHMvcGhvdG8vaW1hZ2UvMjYzNDcwNjAvM2I4YTJjZDJhZjVkZWZlOWI2MGM1YmQwODQxMjg4ZTIuanBnIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJqcGVnIiwicmVzaXplIjp7IndpZHRoIjoxMDIyLCJoZWlnaHQiOjMxMiwiZml0IjoiY292ZXIifSwicm90YXRlIjpudWxsLCJqcGVnIjp7InRyZWxsaXNRdWFudGlzYXRpb24iOnRydWUsIm92ZXJzaG9vdERlcmluZ2luZyI6dHJ1ZSwib3B0aW1pc2VTY2FucyI6dHJ1ZSwicXVhbnRpc2F0aW9uVGFibGUiOjN9fX0=', 'https://cpw.state.co.us/placestogo/parks/ElevenMile');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (5, 'Lake Isabel', 'Custer', 0, 8479, 'A reservoir located in the San Isabel National Forest in Pueblo and Custer counties, in Colorado, United States. The lake is in the Wet Mountains. The lake is open to fishing year round and is regularly stocked by Colorado Parks and Wildlife. Available activities include fishing, hiking, camping and sledding in the winter.', 'https://www.greenhornvalleyview.com/sites/greenhornvalleyview.com/files/styles/article_420/public/2021-12/Deb%20Straub%20Lake%20Isabel.jpg?itok=4IJ5ry3q', 'https://en.wikipedia.org/wiki/Lake_Isabel_(Colorado)');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (6, 'Taylor Reservoir', 'Gunnison', 0, 9335, 'Surrounded by national forest at 9300 feet, Taylor Park Reservoir provides a big mountain playground for a range of outdoor activities. It’s located about 13 miles northeast of Almont in Gunnison County. The Taylor Park Dam was built in 1937 to dam the Taylor River. Water sports are the main draw in Taylor Canyon. From rafting to fishing to boating, it’s all top notch here. Most access the 2000 surface acre Taylor Park Reservoir the front range via Cottonwood Pass in Buena Vista.', 'https://preview.redd.it/eisdysckmyo51.jpg?auto=webp&s=dc03be6e712a801a14e7924e84d432470edc8410', 'https://www.uncovercolorado.com/national-lands/taylor-park-reservoir/');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (7, 'Lake Grandby', 'Grand', 0, 8284, 'The third largest body of water in Colorado. It was created by the erection of Granby Dam, completed in 1950, as part of the Bureau of Reclamation\'s Colorado-Big Thompson Project. Water from Lake Granby is pumped via the Farr Pump plant though a pipeline that empties into a canal connected to Shadow Mountain Reservoir. The Bureau of Reclamation owns Farr Pump plant while Northern Water operates it. On its own, Lake Granby contains approximately 40 miles of shoreline. The lake is popular with anglers and is continually stocked with rainbow trout and kokanee salmon.', 'https://assets.simpleviewinc.com/simpleview/image/upload/c_fill,g_xy_center,h_400,q_75,w_1366,x_498,y_426/v1/clients/granbyco/lake_granby_1677107_1280_1024x682_c1eb06a7-9166-4b96-83a5-e173714f1bce.jpg', 'https://en.wikipedia.org/wiki/Lake_Granby');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (8, 'Grand Lake', 'Grand', 0, 8371, 'Colorado\'s largest and deepest natural lake. It is located in the headwaters of the Colorado River in Grand County, Colorado. On its north shore is located the historic and eponymous town of Grand Lake. The lake was formed during the Pinedale glaciation, which occurred from 30000 BP (before present) to 10000 BP. The glacial terminal moraine created a natural dam. Natural tributaries to the lake are the North Inlet and East Inlet, both of which flow out of Rocky Mountain National Park, which surrounds the lake on three sides. Grand Lake is located 1 mile from the Park\'s western entrance. Grand Lake was named Spirit Lake by the Ute Tribe because they believed the lake\'s cold waters to be the dwelling place of departed souls.', 'https://www.coloradodirectory.com/gograndlake/images/Grand-Lake-Colorado.jpg', 'https://en.wikipedia.org/wiki/Grand_Lake_(Colorado)');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (10, 'Arkansas River', 'Saguache', 1, 5360, 'At 1,469 miles, it is the sixth-longest river in the United States, the second-longest tributary in the Mississippi–Missouri system, and the 45th longest river in the world. Its origin is in the Rocky Mountains in Lake County, Colorado, near Leadville. The headwaters derive from the snowpack in the Sawatch and Mosquito mountain ranges.', 'https://images.alltrails.com/eyJidWNrZXQiOiJhc3NldHMuYWxsdHJhaWxzLmNvbSIsImtleSI6InVwbG9hZHMvcGhvdG8vaW1hZ2UvMjc4ODIxNjYvZmZhMTQ3NTVjY2VmMTZiY2VjMjU5ODE1MDYzZjZlMDEuanBnIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJqcGVnIiwicmVzaXplIjp7IndpZHRoIjoxMDIyLCJoZWlnaHQiOjMxMiwiZml0IjoiY292ZXIifSwicm90YXRlIjpudWxsLCJqcGVnIjp7InRyZWxsaXNRdWFudGlzYXRpb24iOnRydWUsIm92ZXJzaG9vdERlcmluZ2luZyI6dHJ1ZSwib3B0aW1pc2VTY2FucyI6dHJ1ZSwicXVhbnRpc2F0aW9uVGFibGUiOjN9fX0=', 'https://en.wikipedia.org/wiki/Arkansas_River');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (11, 'South Platte River', 'Jefferson', 1, 6428, 'A major river of the American Midwest and the American Southwest/Mountain West. Its drainage basin includes much of the eastern flank of the Rocky Mountains in Colorado, much of the populated region known as the Colorado Front Range and Eastern Plains, and a portion of southeastern Wyoming in the vicinity of the city of Cheyenne. It joins the North Platte River in western Nebraska to form the Platte, which then flows across Nebraska to the Missouri.', 'https://media.springernature.com/full/springer-static/image/art%3A10.1038%2Fnature.2012.11612/MediaObjects/41586_2012_Article_BFnature201211612_Figa_HTML.jpg', 'https://en.wikipedia.org/wiki/South_Platte_River');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (12, 'Colorado River', 'Garfield', 1, 5633, 'The 1,450-mile-long river drains an expansive, arid watershed that encompasses parts of seven U.S. states and two Mexican states. The name Colorado derives from the Spanish language for \"colored reddish\" due to its heavy silt load. Starting in the central Rocky Mountains of Colorado, it flows generally southwest across the Colorado Plateau and through the Grand Canyon before reaching Lake Mead on the Arizona–Nevada border, where it turns south toward the international border. Known for its dramatic canyons, whitewater rapids, and eleven U.S. National Parks, the Colorado River and its tributaries are a vital source of water for 40 million people.', 'https://adamschallau.com/images/xl/Grand-Canyon-Hopi-Salt-Mines-Panoramic-1775-83.jpg', 'https://en.wikipedia.org/wiki/Colorado_River');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (13, 'Gunnison River', 'Montrose', 1, 6516, 'The 180-mile long river flows east to west and has a drainage area of 7,923 square miles according to the USGS. The drainage basin of the Gunnison collects water from different habitats, such as forests and alpine meadows, located the along Continental Divide. As the river flows westward, it carves through the San Juan Mountains. It flows into the Colorado River at Grand Junction.', 'https://www.terragalleria.com/images/np-plateau/blca25173.jpeg', 'https://en.wikipedia.org/wiki/Gunnison_River');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (9, 'Blue Mesa Reservoir', 'Gunnison', 0, 7523, 'A reservoir located on the upper reaches of the Gunnison River in Gunnison County, Colorado. The largest lake located entirely within the state, Blue Mesa Reservoir was created by the construction of Blue Mesa Dam, a 390-foot tall earthen fill dam constructed on the Gunnison by the U.S. Bureau of Reclamation in 1966 for the generation of hydroelectric power. Managed as part of the Curecanti National Recreation Area, a unit of the National Park Service, Blue Mesa Reservoir is the largest lake trout and Kokanee salmon fishery in Colorado.', 'https://images.fineartamerica.com/images-medium-large-5/blue-mesa-reservoir-digital-painting-priscilla-burgers.jpg', 'https://en.wikipedia.org/wiki/Blue_Mesa_Reservoir');
INSERT INTO `body_of_water` (`id`, `name`, `county`, `type`, `elevation_ft`, `description`, `image_url`, `website_url`) VALUES (14, 'Yampa River', 'Routt', 1, 6730, 'Rising in the Rocky Mountains and flowing 250 miles through northwestern Colorado, it is a tributary of the Green River and a major part of the Colorado River system. The Yampa is one of the few free-flowing rivers in the western United States, with only a few small dams and diversions. The name is derived from the Snake Indians word for the Perideridia plant, which has an edible root. John C. Frémont was among the first to record the name \'Yampah\' in entries of his journal from 1843, as he found the plant was particularly abundant in the watershed.', 'https://img.peapix.com/16203984073849602565_UHD.jpg?attachment&modal', 'https://en.wikipedia.org/wiki/Yampa_River');

COMMIT;


-- -----------------------------------------------------
-- Data for table `time_of_day`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishingdb`;
INSERT INTO `time_of_day` (`id`, `timeframe`) VALUES (1, 'Dawn');
INSERT INTO `time_of_day` (`id`, `timeframe`) VALUES (2, 'Morning');
INSERT INTO `time_of_day` (`id`, `timeframe`) VALUES (3, 'Noon');
INSERT INTO `time_of_day` (`id`, `timeframe`) VALUES (4, 'Afternoon');
INSERT INTO `time_of_day` (`id`, `timeframe`) VALUES (5, 'Evening');
INSERT INTO `time_of_day` (`id`, `timeframe`) VALUES (6, 'Dusk');
INSERT INTO `time_of_day` (`id`, `timeframe`) VALUES (7, 'Night');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishingdb`;
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `image_url`, `enabled`, `role`, `created_at`, `last_update`) VALUES (1, 'admin', 'admin', 'Bugs', 'Bunny', 'https://cdn.shopify.com/s/files/1/2960/6098/products/fullsizeoutput_340c_540x.jpg?v=1604728775', 1, 'ADMIN', NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `image_url`, `enabled`, `role`, `created_at`, `last_update`) VALUES (2, 'angler1', 'angler1', 'Taz', 'Devil', 'https://cdn.shopify.com/s/files/1/2960/6098/products/fullsizeoutput_340f_540x.jpg?v=1604728775', 1, 'USER', NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `image_url`, `enabled`, `role`, `created_at`, `last_update`) VALUES (3, 'angler2', 'angler2', 'Sylvester', 'Cat', 'https://static.wikia.nocookie.net/looney-tunes-llc/images/f/ff/Fishing.jpg/revision/latest?cb=20210921180217', 1, 'USER', NULL, NULL);
INSERT INTO `user` (`id`, `username`, `password`, `first_name`, `last_name`, `image_url`, `enabled`, `role`, `created_at`, `last_update`) VALUES (4, 'angler3', 'angler3', 'Elmer', 'Fudd', 'https://i.pinimg.com/236x/54/39/9c/54399c3208e538d9dbf7cff93d58e39b--fishing-sayings-fishing-humor.jpg', 1, 'USER', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `catch_log`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishingdb`;
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (1, '2021-06-17', 2.1, 15, 1, NULL, NULL, 3, 2, 2, 1);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (2, '2021-07-01', 3.4, 16, 1, NULL, NULL, 8, 14, 5, 2);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (3, '2021-08-11', 1.4, 10, 1, NULL, NULL, 5, 1, 3, 3);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (4, '2021-09-15', 2.9, 17, 1, NULL, NULL, 4, 3, 4, 4);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (5, '2022-04-27', 3.2, 15, 1, NULL, NULL, 2, 13, 6, 1);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (6, '2022-05-21', 1.6, 13, 1, NULL, NULL, 1, 7, 1, 2);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (7, '2022-06-12', 6.2, 21, 1, NULL, NULL, 6, 6, 7, 3);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (8, '2022-07-23', 2.3, 14, 1, NULL, NULL, 9, 9, 2, 4);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (9, '2022-08-02', 2.0, 13, 1, NULL, NULL, 7, 12, 6, 1);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (10, '2022-09-16', 2.2, 11, 1, NULL, NULL, 10, 10, 3, 2);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (11, '2023-04-18', 2.5, 13, 1, NULL, NULL, 1, 4, 1, 3);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (12, '2023-05-04', 2.6, 14, 1, NULL, NULL, 2, 5, 4, 4);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (13, '2023-05-28', 3.7, 16, 1, NULL, NULL, 9, 8, 7, 1);
INSERT INTO `catch_log` (`id`, `date`, `weight_lbs`, `length_inch`, `enabled`, `created_at`, `last_update`, `fish_id`, `body_of_water_id`, `time_of_day_id`, `user_id`) VALUES (14, '2023-06-03', 2.4, 11, 1, NULL, NULL, 3, 11, 5, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `available_fish`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishingdb`;
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (1, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (1, 8);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (2, 1);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (2, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (2, 4);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (3, 2);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (3, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (3, 4);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (3, 9);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (4, 1);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (4, 2);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (4, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (4, 6);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (5, 2);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (5, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (6, 2);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (6, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (6, 6);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (6, 9);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (7, 1);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (7, 2);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (7, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (7, 9);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (8, 1);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (8, 2);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (8, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (8, 9);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (9, 2);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (9, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (9, 9);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (10, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (10, 10);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (11, 1);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (11, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (12, 1);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (12, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (12, 7);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (12, 8);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (13, 1);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (13, 2);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (13, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (14, 3);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (14, 6);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (14, 7);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (14, 8);
INSERT INTO `available_fish` (`body_of_water_id`, `fish_id`) VALUES (1, 5);

COMMIT;

