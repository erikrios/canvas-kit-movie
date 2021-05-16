package io.github.erikrios.canvaskitmovie.core.utils

import io.github.erikrios.canvaskitmovie.core.domain.model.Creator
import io.github.erikrios.canvaskitmovie.core.domain.model.Genre
import io.github.erikrios.canvaskitmovie.core.domain.model.Movie
import io.github.erikrios.canvaskitmovie.core.domain.model.TvShow

object DummyData {

    fun generateMovies(): List<Movie> {
        return mutableListOf(
            Movie(
                791373,
                false,
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                70000000,
                listOf(
                    Genre(28, "Action"),
                    Genre(12, "Adventure"),
                    Genre(14, "Fantasy"),
                    Genre(878, "Science Fiction")
                ),
                "en",
                "Zack Snyder's Justice League",
                "Zack Snyder's Justice League",
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                11783.352,
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "2021-03-18",
                0,
                "Released",
                8.7,
                3389, false
            ),
            Movie(
                527774,
                false,
                "/hJuDvwzS0SPlsE6MNFOpznQltDZ.jpg",
                0,
                listOf(
                    Genre(16, "Animation"),
                    Genre(12, "Adventure"),
                    Genre(14, "Fantasy"),
                    Genre(10751, "Family"),
                    Genre(28, "Action")
                ),
                "en",
                "Raya and the Last Dragon",
                "Raya and the Last Dragon",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                3067.315,
                "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                "2021-03-03",
                56482606,
                "Released",
                8.4,
                1628, false
            ),
            Movie(
                581389,
                false,
                "/drulhSX7P5TQlEMQZ3JoXKSDEfz.jpg",
                21000000,
                listOf(
                    Genre(18, "Drama"),
                    Genre(14, "Fantasy"),
                    Genre(878, "Science Fiction")
                ),
                "ko",
                "승리호",
                "Space Sweepers",
                "When the crew of a space junk collector ship called The Victory discovers a humanoid robot named Dorothy that's known to be a weapon of mass destruction, they get involved in a risky business deal which puts their lives at stake.",
                3507.528,
                "/lykPQ7lgrLJPwLlSyetVXsl2wDf.jpg",
                "2021-02-05",
                0,
                "Released",
                7.2,
                417, false
            ),
            Movie(
                793723,
                false,
                "/gzJnMEMkHowkUndn9gCr8ghQPzN.jpg",
                0,
                listOf(
                    Genre(53, "Thriller"),
                    Genre(28, "Action"),
                    Genre(18, "Drama")
                ),
                "fr",
                "Sentinelle",
                "Sentinelle",
                "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister.",
                2266.597,
                "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                "2021-03-05",
                0,
                "Released",
                6.0,
                264, false
            ),
            Movie(
                587807,
                false,
                "/fev8UFNFFYsD5q7AcYS8LyTzqwl.jpg",
                50000000,
                listOf(
                    Genre(28, "Action"),
                    Genre(35, "Comedy"),
                    Genre(10751, "Family"),
                    Genre(16, "Animation")
                ),
                "en",
                "Tom & Jerry",
                "Tom & Jerry",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she can’t evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse.",
                1944.478,
                "/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "2021-02-11",
                66890000,
                "Released",
                7.4,
                962, false
            ),
            Movie(
                458576,
                false,
                "/8tNX8s3j1O0eqilOQkuroRLyOZA.jpg",
                60000000,
                listOf(
                    Genre(14, "Fantasy"),
                    Genre(28, "Action"),
                    Genre(12, "Adventure")
                ),
                "en",
                "Monster Hunter",
                "Monster Hunter",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                1900.233,
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "2020-12-03",
                25814306,
                "Released",
                7.2,
                1229, false
            ),
            Movie(
                535313,
                false,
                "/iopYFB1b6Bh7FWZh3onQhph1sih.jpg",
                200000000,
                listOf(
                    Genre(28, "Action"),
                    Genre(878, "Science Fiction")
                ),
                "en",
                "Godzilla vs. Kong",
                "Godzilla vs. Kong",
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                2675.469,
                "/asmxTLzVSLTkIwybSB0DJHDKw8o.jpg",
                "2021-03-24",
                0,
                "Released",
                7.6,
                35, false
            ),
            Movie(
                468552,
                false,
                "/egg7KFi18TSQc1s24RMmR9i2zO6.jpg",
                200000000,
                listOf(
                    Genre(14, "Fantasy"),
                    Genre(28, "Action"),
                    Genre(12, "Adventure")
                ),
                "en",
                "Wonder Woman 1984",
                "Wonder Woman 1984",
                "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
                1693.774,
                "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
                "2020-12-16",
                165160000,
                "Released",
                6.8,
                4446, false
            ),
            Movie(
                587996,
                false,
                "/6TPZSJ06OEXeelx1U1VIAt0j9Ry.jpg",
                0,
                listOf(
                    Genre(28, "Action"),
                    Genre(80, "Crime"),
                    Genre(53, "Thriller")
                ),
                "es",
                "Bajocero",
                "Below Zero",
                "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures.",
                1644.589,
                "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
                "2021-01-29",
                0,
                "Released",
                6.4,
                468, false
            ),
            Movie(
                544401,
                false,
                "/uQtqiAu2bBlokqjlURVLEha6zoi.jpg",
                0,
                listOf(
                    Genre(80, "Crime"),
                    Genre(18, "Drama")
                ),
                "en",
                "Cherry",
                "Cherry",
                "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world.",
                1629.653,
                "/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg",
                "2021-02-26",
                0,
                "Released",
                7.7,
                344, false
            ),
            Movie(
                775996,
                false,
                "/lOSdUkGQmbAl5JQ3QoHqBZUbZhC.jpg",
                0,
                listOf(
                    Genre(53, "Thriller"),
                    Genre(28, "Action"),
                    Genre(878, "Science Fiction")
                ),
                "en",
                "Outside the Wire",
                "Outside the Wire",
                "In the near future, a drone pilot is sent into a deadly militarized zone and must work with an android officer to locate a doomsday device.",
                1096.334,
                "/6XYLiMxHAaCsoyrVo38LBWMw2p8.jpg",
                "2021-01-15",
                0,
                "Released",
                6.5,
                894, false
            ),
            Movie(
                484718,
                false,
                "/vKzbIoHhk1z9DWYi8kyFe9Gg0HF.jpg",
                60000000,
                listOf(
                    Genre(35, "Comedy")
                ),
                "en",
                "Coming 2 America",
                "Coming 2 America",
                "Prince Akeem Joffer is set to become King of Zamunda when he discovers he has a son he never knew about in America – a street savvy Queens native named Lavelle. Honoring his royal father's dying wish to groom this son as the crown prince, Akeem and Semmi set off to America once again.",
                878.181,
                "/nWBPLkqNApY5pgrJFMiI9joSI30.jpg",
                "2021-03-05",
                0,
                "Released",
                6.9,
                1012, false
            ),
            Movie(
                649087,
                false,
                "/7KL4yJ4JsbtS1BNRilUApLvMnc5.jpg",
                0,
                listOf(
                    Genre(18, "Drama"),
                    Genre(53, "Thriller")
                ),
                "sv",
                "Red Dot",
                "Red Dot",
                "On a hiking trip to rekindle their marriage, a couple find themselves fleeing for their lives in the unforgiving wilderness from an unknown shooter.",
                936.356,
                "/xZ2KER2gOHbuHP2GJoODuXDSZCb.jpg",
                "2021-02-11",
                0,
                "Released",
                6.2,
                365, false
            ),
            Movie(
                651571,
                false,
                "/nz8xWrTKZzA5A7FgxaM4kfAoO1W.jpg",
                0,
                listOf(
                    Genre(878, "Science Fiction"),
                    Genre(28, "Action")
                ),
                "en",
                "Breach",
                "Breach",
                "A hardened mechanic must stay awake and maintain an interstellar ark fleeing the dying planet Earth with a few thousand lucky souls on board... the last of humanity. Unfortunately, humans are not the only passengers. A shapeshifting alien creature has taken residence, its only goal is to kill as many people as possible. The crew must think quickly to stop this menace before it destroys mankind.",
                917.923,
                "/13B6onhL6FzSN2KaNeQeMML05pS.jpg",
                "2020-12-17",
                0,
                "Released",
                4.5,
                323, false
            ),
            Movie(
                602269,
                false,
                "/vfuzELmhBjBTswXj2Vqxnu5ge4g.jpg",
                30000000,
                listOf(
                    Genre(53, "Thriller"),
                    Genre(80, "Crime")
                ),
                "en",
                "The Little Things",
                "The Little Things",
                "Deputy Sheriff Joe \\\"Deke\\\" Deacon joins forces with Sgt. Jim Baxter to search for a serial killer who's terrorizing Los Angeles. As they track the culprit, Baxter is unaware that the investigation is dredging up echoes of Deke's past, uncovering disturbing secrets that could threaten more than his case.",
                885.01,
                "/c7VlGCCgM9GZivKSzBgzuOVxQn7.jpg",
                "2021-01-28",
                23205000,
                "Released",
                6.4,
                632, false
            ),
            Movie(
                590706,
                false,
                "/jeAQdDX9nguP6YOX6QSWKDPkbBo.jpg",
                23000000,
                listOf(
                    Genre(14, "Fantasy"),
                    Genre(28, "Action"),
                    Genre(878, "Science Fiction")
                ),
                "en",
                "Jiu Jitsu",
                "Jiu Jitsu",
                "Every six years, an ancient order of jiu-jitsu fighters joins forces to battle a vicious race of alien invaders. But when a celebrated war hero goes down in defeat, the fate of the planet and mankind hangs in the balance.",
                853.208,
                "/eLT8Cu357VOwBVTitkmlDEg32Fs.jpg",
                "2020-11-20",
                99924,
                "Released",
                5.2,
                340, false
            ),
            Movie(
                522444,
                false,
                "/fRrpOILyXuWaWLmqF7kXeMVwITQ.jpg",
                0,
                listOf(
                    Genre(27, "Horror"),
                    Genre(53, "Thriller"),
                    Genre(12, "Adventure"),
                    Genre(9648, "Mystery")
                ),
                "en",
                "Black Water: Abyss",
                "Black Water: Abyss",
                "An adventure-loving couple convince their friends to explore a remote, uncharted cave system in the forests of Northern Australia. With a tropical storm approaching, they abseil into the mouth of the cave, but when the caves start to flood, tensions rise as oxygen levels fall and the friends find themselves trapped. Unknown to them, the storm has also brought in a pack of dangerous and hungry crocodiles.",
                858.945,
                "/95S6PinQIvVe4uJAd82a2iGZ0rA.jpg",
                "2020-07-09",
                0,
                "Released",
                5.0,
                170, false
            ),
            Movie(
                755812,
                false,
                "/yR27bZPIkNhpGEIP3jKV2EifTgo.jpg",
                0,
                listOf(
                    Genre(16, "Animation"),
                    Genre(10751, "Family")
                ),
                "fr",
                "Miraculous World: New York, United HeroeZ",
                "Miraculous World: New York, United HeroeZ",
                "During a school field trip, Ladybug and Cat Noir meet the American superheroes, whom they have to save from an akumatised super-villain. They discover that Miraculous exist in the United States too.",
                882.522,
                "/50AlM1eAqXHEG8Z5lzrTbWyiew9.jpg",
                "2020-09-26",
                0,
                "Released",
                8.3,
                529, false
            ),
            Movie(
                664767,
                false,
                "/9xeEGUZjgiKlI69jwIOi0hjKUIk.jpg",
                0,
                listOf(
                    Genre(14, "Fantasy"),
                    Genre(28, "Action"),
                    Genre(12, "Adventure"),
                    Genre(16, "Animation")
                ),
                "en",
                "Mortal Kombat Legends: Scorpion's Revenge",
                "Mortal Kombat Legends: Scorpion's Revenge",
                "After the vicious slaughter of his family by stone-cold mercenary Sub-Zero, Hanzo Hasashi is exiled to the torturous Netherrealm. There, in exchange for his servitude to the sinister Quan Chi, he’s given a chance to avenge his family – and is resurrected as Scorpion, a lost soul bent on revenge. Back on Earthrealm, Lord Raiden gathers a team of elite warriors – Shaolin monk Liu Kang, Special Forces officer Sonya Blade and action star Johnny Cage – an unlikely band of heroes with one chance to save humanity. To do this, they must defeat Shang Tsung’s horde of Outworld gladiators and reign over the Mortal Kombat tournament.",
                798.951,
                "/4VlXER3FImHeFuUjBShFamhIp9M.jpg",
                "2020-04-12",
                0,
                "Released",
                8.4,
                805, false
            ),
            Movie(
                529203,
                false,
                "/cjaOSjsjV6cl3uXdJqimktT880L.jpg",
                65000000,
                listOf(
                    Genre(10751, "Family"),
                    Genre(14, "Fantasy"),
                    Genre(16, "Animation"),
                    Genre(35, "Comedy")
                ),
                "en",
                "The Croods: A New Age",
                "The Croods: A New Age",
                "Searching for a safer habitat, the prehistoric Crood family discovers an idyllic, walled-in paradise that meets all of its needs. Unfortunately, they must also learn to live with the Bettermans -- a family that's a couple of steps above the Croods on the evolutionary ladder. As tensions between the new neighbors start to rise, a new threat soon propels both clans on an epic adventure that forces them to embrace their differences, draw strength from one another, and survive together.",
                692.142,
                "/tbVZ3Sq88dZaCANlUcewQuHQOaE.jpg",
                "2020-11-25",
                135076090,
                "Released",
                7.5,
                1791, false
            )
        )
    }

    fun generateTvShows(): List<TvShow> {
        return listOf(
            TvShow(
                88396,
                "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                listOf(
                    Genre(10765, "Sci-Fi & Fantasy"),
                    Genre(10759, "Action & Adventure"),
                    Genre(18, "Drama")
                ),
                "en",
                "The Falcon and the Winter Soldier",
                "The Falcon and the Winter Soldier",
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                3612.888,
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "2021-03-19",
                "Returning Series",
                7.7,
                1657,
                listOf(
                    Creator(1868712, "Malcolm Spellman", null)
                ), false
            ),
            TvShow(
                60735,
                "/JB17sIsU53NuWVUecOwrCA0CUp.jpg",
                listOf(
                    Genre(18, "Drama"),
                    Genre(10765, "Sci-Fi & Fantasy")
                ),
                "en",
                "The Flash",
                "The Flash",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                1510.931,
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "2014-10-07",
                "Returning Series",
                7.7,
                7130,
                listOf(
                    Creator(88967, "Greg Berlanti", "/1T24SiIIDMa9gAmq7QaYwZywq4y.jpg"),
                    Creator(211962, "Geoff Johns", "/1hiQjkIkeFoiwvD4yIk2Dq6tnOa.jpg"),
                    Creator(1216615, "Andrew Kreisberg", null)
                ), false
            ),
            TvShow(
                69050,
                "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                listOf(
                    Genre(9648, "Mystery"),
                    Genre(18, "Drama"),
                    Genre(80, "Crime")
                ),
                "en",
                "Riverdale",
                "Riverdale",
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                1453.784,
                "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "2017-01-26",
                "Returning Series",
                8.6,
                10711,
                listOf(
                    Creator(1098032, "Roberto Aguirre-Sacasa", "/PtJSrAk7LDqaV5jExCYl10lcsN.jpg")
                ), false
            ),
            TvShow(
                95057,
                "/gmbsR4SvYhhj4SvLAlTKxIkFxp9.jpg",
                listOf(
                    Genre(10759, "Action & Adventure"),
                    Genre(10765, "Sci-Fi & Fantasy")
                ),
                "en",
                "Superman & Lois",
                "Superman & Lois",
                "After years of facing megalomaniacal supervillains, monsters wreaking havoc on Metropolis, and alien invaders intent on wiping out the human race, The Man of Steel aka Clark Kent and Lois Lane come face to face with one of their greatest challenges ever: dealing with all the stress, pressures and complexities that come with being working parents in today's society.",
                942.56,
                "/6SJppowm7cdQgLkvoTlnTUSbjr9.jpg",
                "2021-02-23",
                "Returning Series",
                8.3,
                591,
                listOf(
                    Creator(88967, "Greg Berlanti", "/1T24SiIIDMa9gAmq7QaYwZywq4y.jpg"),
                    Creator(209763, "Todd Helbing", "/wNwx7IQs1HHcMuR7gsBcKPEfFYd.jpg")
                ), false
            ),
            TvShow(
                85271,
                "/rFLF2QTZL37Yjdc6kmV0PbrYz3w.jpg",
                listOf(
                    Genre(10765, "Sci-Fi & Fantasy"),
                    Genre(9648, "Mystery"),
                    Genre(18, "Drama")
                ),
                "en",
                "WandaVision",
                "WandaVision",
                "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
                1071.358,
                "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
                "2021-01-15",
                "Ended",
                8.5,
                7723,
                listOf(
                    Creator(123132, "Jac Schaeffer", null)
                ), false
            ),
            TvShow(
                1416,
                "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                listOf(
                    Genre(18, "Drama")
                ),
                "en",
                "Grey's Anatomy",
                "Grey's Anatomy",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                967.866,
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "2005-03-27",
                "Returning Series",
                8.2,
                5637,
                listOf(
                    Creator(25539, "Shonda Rhimes", "/pIQbVxfjNwZ0DaAjo3q107nng1b.jpg")
                ), false
            ),
            TvShow(
                71712,
                "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                listOf(
                    Genre(18, "Drama")
                ),
                "en",
                "The Good Doctor",
                "The Good Doctor",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                1105.6,
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "2017-09-25",
                "Returning Series",
                8.6,
                7222,
                listOf(
                    Creator(169061, "David Shore", "/yUwAL9wOJoS9caz9GusyHsrad8J.jpg")
                ), false
            ),
            TvShow(
                1402,
                "/uro2Khv7JxlzXtLb8tCIbRhkb9E.jpg",
                listOf(
                    Genre(10759, "Action & Adventure"),
                    Genre(18, "Drama"),
                    Genre(10765, "Sci-Fi & Fantasy")
                ),
                "en",
                "The Walking Dead",
                "The Walking Dead",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                891.67,
                "/rqeYMLryjcawh2JeRpCVUDXYM5b.jpg",
                "2010-10-31",
                "Returning Series",
                8.0,
                10286,
                listOf(
                    Creator(4027, "Frank Darabont", "/wmUGB2vPuYQKPWNrK24SonBtyJY.jpg")
                ), false
            ),
            TvShow(
                63174,
                "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                listOf(
                    Genre(80, "Crime"),
                    Genre(10765, "Sci-Fi & Fantasy")
                ),
                "en",
                "Lucifer",
                "Lucifer",
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                820.827,
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "2016-01-25",
                "Returning Series",
                8.5,
                8009,
                listOf(
                    Creator(1222585, "Tom Kapinos", "/ol7GfeO0OIDCWGYzlg1LDLmwluO.jpg")
                ), false
            ),
            TvShow(
                117023,
                "/e8m25rmxcCtoNh3KQHtTFfoFSEF.jpg",
                listOf(
                    Genre(10759, "Action & Adventure"),
                    Genre(80, "Crime"),
                    Genre(18, "Drama")
                ),
                "es",
                "Sky Rojo",
                "Sky Rojo",
                "On the run from their pimp and his henchmen, three women embark on a wild and crazy journey in search of freedom.",
                708.515,
                "/uTFX9V2dct1nKjG6zhNiThPm8Tp.jpg",
                "2021-03-19",
                "Returning Series",
                8.2,
                84,
                listOf(
                    Creator(82725, "Álex Pina", "/hMKcFPRKo0I4WLBvkvppyBFDGr8.jpg"),
                    Creator(1637084, "Esther Martínez Lobato", null)
                ), false
            ),
            TvShow(
                79460,
                "/fRYwdeNjMqC30EhofPx5PlDpdun.jpg",
                listOf(
                    Genre(10765, "Sci-Fi & Fantasy"),
                    Genre(18, "Drama")
                ),
                "en",
                "Legacies",
                "Legacies",
                "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted.",
                581.108,
                "/qTZIgXrBKURBK1KrsT7fe3qwtl9.jpg",
                "2018-10-25",
                "Returning Series",
                8.6,
                1716,
                listOf(
                    Creator(1214616, "Julie Plec", null)
                ), false
            ),
            TvShow(
                65334,
                "/9RqliZcoDEjSEISeA0LY9meAiNv.jpg",
                listOf(
                    Genre(10759, "Action & Adventure"),
                    Genre(16, "Animation"),
                    Genre(10762, "Kids")
                ),
                "fr",
                "Miraculous, les aventures de Ladybug et Chat Noir",
                "Miraculous: Tales of Ladybug & Cat Noir",
                "Normal high school kids by day, protectors of Paris by night! Miraculous follows the heroic adventures of Marinette and Adrien as they transform into Ladybug and Cat Noir and set out to capture akumas, creatures responsible for turning the people of Paris into villains. But neither hero knows the other’s true identity – or that they’re classmates!",
                421.339,
                "/qxbW47zmgFyBVmZSIqD9NA1DEjT.jpg",
                "2015-10-19",
                "Returning Series",
                7.8,
                2181,
                listOf(
                    Creator(1565301, "Thomas Astruc", null)
                ), false
            ),
            TvShow(
                114695,
                "/wAEWZm2pSopAbqE5dQWE0ET8aR5.jpg",
                listOf(
                    Genre(99, "Documentary")
                ),
                "en",
                "Marvel Studios: Legends",
                "Marvel Studios: Legends",
                "Revisit the epic heroes, villains and moments from across the MCU in preparation for the stories still to come. Each dynamic segment feeds directly into the upcoming series — setting the stage for future events. This series weaves together the many threads that constitute the unparalleled Marvel Cinematic Universe.",
                501.827,
                "/EpDuYIK81YtCUT3gH2JDpyj8Qk.jpg",
                "2021-01-08",
                "Returning Series",
                7.7,
                353,
                listOf(), false
            ),
            TvShow(
                76231,
                "/9XUIsRUB7V3iEoLSKxZCqPvbKKW.jpg",
                listOf(
                    Genre(80, "Crime"),
                    Genre(18, "Drama")
                ),
                "en",
                "Mayans M.C.",
                "Mayans M.C.",
                "Set in the aftermath of Jax Teller’s death, Ezekiel \\\"EZ\\\" Reyes is fresh out of prison and a prospect in the Mayans M.C. charter on the Cali/Mexi border. Now, EZ must carve out his new identity in a town where he was once the golden boy with the American Dream in his grasp.",
                353.704,
                "/gaKhfksFK24N19bjlFpJxamYZ02.jpg",
                "2018-09-04",
                "Returning Series",
                7.8,
                137,
                listOf(
                    Creator(200043, "Kurt Sutter", "/A4c9xpj2VuZXGvSv6z1S912Xwnd.jpg"),
                    Creator(1026085, "Elgin James", null)
                ), false
            ),
            TvShow(
                96580,
                "/fPF6h8LLtZ40NRqkHfx2DvFbmkW.jpg",
                listOf(
                    Genre(10765, "Sci-Fi & Fantasy")
                ),
                "en",
                "Resident Alien",
                "Resident Alien",
                "Crash-landed alien Harry takes on the identity of a small-town Colorado doctor. Arriving with a secret mission, he starts off living a simple life…but things get a bit rocky when he’s roped into solving a local murder and realizes he needs to assimilate into his new world. As he does, he begins to wrestle with the moral dilemma of his mission and asking the big life questions like: “Are human beings worth saving?” and “Why do they fold their pizza before eating it?”",
                464.601,
                "/bG5aqfT5lTHuSbcQofNHtH0RyyP.jpg",
                "2021-01-27",
                "Returning Series",
                8.8,
                77,
                listOf(
                    Creator(170819, "Chris Sheridan", null)
                ), false
            ),
            TvShow(
                44217,
                "/aq2yEMgRQBPfRkrO0Repo2qhUAT.jpg",
                listOf(
                    Genre(10759, "Action & Adventure"),
                    Genre(18, "Drama")
                ),
                "en",
                "Vikings",
                "Vikings",
                "The adventures of Ragnar Lothbrok, the greatest hero of his age. The series tells the sagas of Ragnar's band of Viking brothers and his family, as he rises to become King of the Viking tribes. As well as being a fearless warrior, Ragnar embodies the Norse traditions of devotion to the gods. Legend has it that he was a direct descendant of Odin, the god of war and warriors.",
                456.468,
                "/bQLrHIRNEkE3PdIWQrZHynQZazu.jpg",
                "2013-03-03",
                "Ended",
                8.0,
                4249,
                listOf(
                    Creator(37631, "Michael Hirst", null)
                ), false
            ),
            TvShow(
                77169,
                "/gL8myjGc2qrmqVosyGm5CWTir9A.jpg",
                listOf(
                    Genre(10759, "Action & Adventure"),
                    Genre(18, "Drama")
                ),
                "en",
                "Cobra Kai",
                "Cobra Kai",
                "This Karate Kid sequel series picks up 30 years after the events of the 1984 All Valley Karate Tournament and finds Johnny Lawrence on the hunt for redemption by reopening the infamous Cobra Kai karate dojo. This reignites his old rivalry with the successful Daniel LaRusso, who has been working to maintain the balance in his life without mentor Mr. Miyagi.",
                552.684,
                "/obLBdhLxheKg8Li1qO11r2SwmYO.jpg",
                "2018-05-02",
                "Returning Series",
                8.1,
                2809,
                listOf(
                    Creator(68844, "Hayden Schlossberg", null),
                    Creator(347335, "Josh Heald", null),
                    Creator(1801553, "John Hurwitz", null)
                ), false
            ),
            TvShow(
                67855,
                "/Aof7R1if9jKhHCk6M7UGyEQWQSk.jpg",
                listOf(
                    Genre(80, "Crime"),
                    Genre(18, "Drama")
                ),
                "en",
                "The Rookie",
                "Cobra Kai",
                "Starting over isn’t easy, especially for small-town guy John Nolan who, after a life-altering incident, is pursuing his dream of being an LAPD officer. As the force’s oldest rookie, he’s met with skepticism from some higher-ups who see him as just a walking midlife crisis.",
                389.734,
                "/6hChiX0vIjWY4y2kz1WndHVMsDu.jpg",
                "2018-10-16",
                "Returning Series",
                8.0,
                416,
                listOf(), false
            ),
            TvShow(
                1399,
                "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                listOf(
                    Genre(10765, "Sci-Fi & Fantasy"),
                    Genre(18, "Drama"),
                    Genre(10759, "Action & Adventure")
                ),
                "en",
                "Game of Thrones",
                "Game of Thrones",
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                430.883,
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "2011-04-17",
                "Ended",
                8.4,
                13643,
                listOf(
                    Creator(9813, "David Benioff", "/xvNN5huL0X8yJ7h3IZfGG4O2zBD.jpg"),
                    Creator(228068, "D. B. Weiss", "/2RMejaT793U9KRk2IEbFfteQntE.jpg")
                ), false
            ),
            TvShow(
                18165,
                "/k7T9xRyzP41wBVNyNeLmh8Ch7gD.jpg",
                listOf(
                    Genre(18, "Drama"),
                    Genre(10765, "Sci-Fi & Fantasy")
                ),
                "en",
                "The Vampire Diaries",
                "The Vampire Diaries",
                "The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.",
                369.711,
                "/aBkVgChtyyJaHyZh1gfd8DbzQon.jpg",
                "2009-09-10",
                "Ended",
                8.3,
                5487,
                listOf(
                    Creator(26458, "Kevin Williamson", "/jWgZZoKBU1WFaCO5C7jpMBZ7xJs.jpg"),
                    Creator(1214616, "Julie Plec", "/kQeuOgZBKDs80W4Ssm91x6zAdWu.jpg")
                ), false
            )
        )
    }

    fun getMovieById(id: Int): Movie? = generateMovies().find { it.id == id }

    fun getTvShowById(id: Int): TvShow? = generateTvShows().find { it.id == id }
}