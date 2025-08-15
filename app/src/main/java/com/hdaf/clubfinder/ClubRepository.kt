package com.hdaf.clubfinder

object ClubRepository {
    // Define colors for categories
    private const val COLOR_TECHNICAL = "#4285F4" // Google Blue
    private const val COLOR_CULTURAL = "#DB4437" // Google Red
    private const val COLOR_SOCIAL = "#0F9D58"   // Google Green
    private const val COLOR_SPIRITUAL = "#7E57C2" // Deep Purple

    val clubs = listOf(
        Club(
            id = 1,
            name = "Arts Club",
            fullName = "The Fine Arts Club",
            department = "General",
            interest = "Cultural",
            logo = "🎨",
            colorHex = COLOR_CULTURAL,
            description = "The Art Club provides a supportive and collaborative environment for members to explore their creative talents, learn new techniques, and showcase their artwork through various events and workshops.",
            leaders = "Prof. Lokesh Heda (ENU)\nProf. Yoginee Pethe (MCA)",
            contact = "arts@rcoem.edu",
            keywords = "art paint sketch drawing"
        ),
        Club(
            id = 2,
            name = "Code Breakers",
            fullName = "Code Breakers Club",
            department = "Computer Science",
            interest = "Technical",
            logo = "🕵️",
            colorHex = COLOR_TECHNICAL,
            description = "Aims to establish a coding culture on campus, reaching every student passionate about coding through competitions, open-source projects, and workshops on the latest technologies.",
            leaders = "Prof. Y. Z. Ahmed",
            contact = "codebreakers@rcoem.edu",
            keywords = "coding development blockchain ctf"
        ),
        Club(
            id = 3,
            name = "Dance Club",
            fullName = "RCOEM Dance Crew",
            department = "General",
            interest = "Cultural",
            logo = "💃",
            colorHex = COLOR_CULTURAL,
            description = "Active in various cultural events like Aaruni and Pratishruti, the club covers diverse dance styles including classical, Bollywood, and hip-hop, nurturing talent for inter-college competitions.",
            leaders = "Prof. Devishree Naidu (CSE)\nProf. Sunita Dhote (MBA)",
            contact = "dance@rcoem.edu",
            keywords = "dance hip-hop classical"
        ),
        Club(
            id = 15,
            name = "Drama Club",
            fullName = "Drama Club",
            department = "General",
            interest = "Cultural",
            logo = "🎭",
            colorHex = COLOR_CULTURAL,
            description = "Provides a platform for budding actors, scriptwriters, and directors. The club organizes drama workshops and stages performances like 'ROLE NO.' and 'QIRDAAR' for various college events.",
            leaders = "Prof. Akanksha P. Deshpande (Mentor)\nProf. Snehal Laddha (ENU)",
            contact = "drama@rcoem.edu",
            keywords = "drama theatre acting script qirdaar"
        ),
        Club(
            id = 4,
            name = "Embedded Club",
            fullName = "Embedded Systems & Robotics Club",
            department = "Electronics",
            interest = "Technical",
            logo = "🤖",
            colorHex = COLOR_TECHNICAL,
            description = "Provides space and infrastructure for technical minds to carry out embedded projects in fields like automotive, robotics, and IoT for various national-level competitions.",
            leaders = "Prof. R. A. Deshmukh",
            contact = "embedded@rcoem.edu",
            keywords = "robotics embedded systems iot"
        ),
        Club(
            id = 5,
            name = "Music Club",
            fullName = "The Octaves",
            department = "General",
            interest = "Cultural",
            logo = "🎵",
            colorHex = COLOR_CULTURAL,
            description = "Started in 1988, this club is a platform for students to showcase their talent in vocal and instrumental music through performances, competitions, and jam sessions.",
            leaders = "Prof. Charanjeet Singh Dadiyala (CSU)\nKanak Wadhwani (MBA)",
            contact = "music@rcoem.edu",
            keywords = "music band sing instruments"
        ),
        Club(
            id = 6,
            name = "Photography Club",
            fullName = "The Shutterbugs",
            department = "General",
            interest = "Cultural",
            logo = "📸",
            colorHex = COLOR_CULTURAL,
            description = "An aesthetic art club established in 2015 to promote amateur and professional photography among students by covering various campus events and organizing workshops.",
            leaders = "Prof. Lokesh Heda (ENU)\nProf. Yoginee Pethe (MCA)",
            contact = "photography@rcoem.edu",
            keywords = "photography camera photo"
        ),
        Club(
            id = 7,
            name = "Readers' Club",
            fullName = "Readers' Reverie Literary Club",
            department = "General",
            interest = "Cultural",
            logo = "📚",
            colorHex = COLOR_CULTURAL,
            description = "A forum for students with a passion for literature to discuss ideas, hone articulation skills, and engage with differing viewpoints. The club values freedom of expression and open-mindedness.",
            leaders = "Shantanu Kulkarni (MUE)\nPratishruti Singh (Humanities)",
            contact = "literary@rcoem.edu",
            keywords = "books reading literature"
        ),
        Club(
            id = 8,
            name = "Robotics Club",
            fullName = "RCOEM Robotics Division",
            department = "Mechanical",
            interest = "Technical",
            logo = "🦾",
            colorHex = COLOR_TECHNICAL,
            description = "Aims to fuel awareness in the field of Robotics. The club consists of self-motivated students who learn, share, and grow by participating in national competitions like E-yantra and ROBOCON.",
            leaders = "Ashlesh Jaiswal (ECU)\nPankaj Joshi (ENU)",
            contact = "robotics@rcoem.edu",
            keywords = "robotics automation mechanical"
        ),
        Club(
            id = 9,
            name = "Rotaract Club",
            fullName = "Rotaract Club of RCOEM",
            department = "General",
            interest = "Social",
            logo = "🤝",
            colorHex = COLOR_SOCIAL,
            description = "A service-oriented club focused on community development, leadership skills, and professional networking. Organizes events like blood donation drives and cleanliness campaigns.",
            leaders = "Dr. Q. R. Joshi",
            contact = "rotaract@rcoem.edu",
            keywords = "social service community volunteering"
        ),
        Club(
            id = 10,
            name = "SAE India Club",
            fullName = "Society of Automotive Engineers – RBU Collegiate Club",
            department = "Mechanical",
            interest = "Technical",
            logo = "🏎️",
            colorHex = COLOR_TECHNICAL,
            description = "A premier student-led club focused on innovation in automotive engineering. Members design, build, and race vehicles for competitions like BAJA SAE, SUPRA SAE, and Efficycle.",
            leaders = "Dr. I. J. Reddy",
            contact = "sae@rbunagpur.in",
            keywords = "automotive engineering baja supra"
        ),
        Club(
            id = 11,
            name = "Spiritual Club",
            fullName = "The Inner Circle",
            department = "General",
            interest = "Spiritual",
            logo = "🧘",
            colorHex = COLOR_SPIRITUAL,
            description = "Discover. Connect. Reflect. The Spiritual Club offers a safe space for students to explore spiritual beliefs through interfaith dialogue, mindfulness, meditation, and workshops.",
            leaders = "Dr. Bhalchandra Madhao Hardas",
            contact = "spiritual@rcoem.edu",
            keywords = "meditation yoga peace"
        ),
        Club(
            id = 12,
            name = "Technical Club",
            fullName = "General Technical Club",
            department = "General",
            interest = "Technical",
            logo = "🛠️",
            colorHex = COLOR_TECHNICAL,
            description = "Launched in 2015, this club provides a common platform for interdisciplinary technical activities, aiming to strengthen students by integrating their skills in various fields of Engineering & Technology.",
            leaders = "Prof. Prashant B. Shiwalkar (Mentor)\nAshlesh Jaiswal (ECU)",
            contact = "techclub@rcoem.edu",
            keywords = "technology projects general"
        ),
        Club(
            id = 13,
            name = "NSS",
            fullName = "National Service Scheme",
            department = "General",
            interest = "Social",
            logo = "🕊️",
            colorHex = COLOR_SOCIAL,
            description = "Motto: “NOT ME, BUT YOU”\n\nA government public service program aimed at developing a student’s personality through hands-on community service, ensuring the needy get help to enhance their standard of living.",
            leaders = "Dr. Amit Anurag (Program Officer)",
            contact = "nss@rcoem.edu",
            keywords = "nss social service community volunteering"
        ),
        Club(
            id = 14,
            name = "REEF",
            fullName = "Renewable Energy & Environment Forum",
            department = "General",
            interest = "Technical",
            logo = "🌿",
            colorHex = COLOR_TECHNICAL,
            description = "Promoting awareness and projects related to green technology, sustainability, and environmental conservation among students and the community.",
            leaders = "Dr. J. K. Lamba",
            contact = "reef@rcoem.edu",
            keywords = "environment green energy"
        )
    )
}
