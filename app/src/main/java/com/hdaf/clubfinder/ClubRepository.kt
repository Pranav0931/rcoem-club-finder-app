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
            description = "The Art Club was established on 5 August 2022. It provides a supportive and collaborative environment for members to explore their creative talents, learn new techniques, and showcase their artwork.",
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
            description = "Focuses on cybersecurity, ethical hacking, and cryptography. The club prepares students for Capture The Flag (CTF) competitions and promotes awareness about digital security.",
            leaders = "Prof. Y. Z. Ahmed",
            contact = "codebreakers@rcoem.edu",
            keywords = "cybersecurity hacking ctf"
        ),
        Club(
            id = 3,
            name = "Dance Club",
            fullName = "RCOEM Dance Crew",
            department = "General",
            interest = "Cultural",
            logo = "💃",
            colorHex = COLOR_CULTURAL,
            description = "From classical to hip-hop, this club is for everyone who loves to dance. It provides a platform for students to showcase their talent in various inter-college and intra-college events.",
            leaders = "Ms. A. Chopra",
            contact = "dance@rcoem.edu",
            keywords = "dance hip-hop classical"
        ),
        Club(
            id = 4,
            name = "Embedded Club",
            fullName = "Embedded Systems & Robotics Club",
            department = "Electronics",
            interest = "Technical",
            logo = "🤖",
            colorHex = COLOR_TECHNICAL,
            description = "Dive deep into the world of microcontrollers, embedded C, and building autonomous robots. The club focuses on hands-on projects and practical learning.",
            leaders = "Dr. B. C. Patel",
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
            description = "Brings together musicians and vocalists for jam sessions, band performances, and music theory classes. The club is a hub for all musical activities on campus.",
            leaders = "Prof. O. P. Singh",
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
            description = "Capture moments and learn the art of photography. The club organizes regular photo walks, workshops on editing, and exhibitions to showcase student work.",
            leaders = "Mr. D. E. Francis",
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
            description = "A heaven for book lovers. The club holds regular discussions, author talks, poetry slams, and various literary events to promote reading culture.",
            leaders = "Dr. F. G. Mishra",
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
            description = "Aims to fuel awareness in the field of Robotics. Students design, build, and program robots for national competitions like Robocon and other tech fests.",
            leaders = "Dr. G. H. Jain",
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
            description = "A service-oriented club focused on community development, leadership skills, and professional networking. Organizes blood donation drives, cleanliness campaigns, etc.",
            leaders = "Dr. Q. R. Joshi",
            contact = "rotaract@rcoem.edu",
            keywords = "social service community volunteering"
        ),
        Club(
            id = 10,
            name = "SAE India Club",
            fullName = "Society of Automotive Engineers",
            department = "Mechanical",
            interest = "Technical",
            logo = "🏎️",
            colorHex = COLOR_TECHNICAL,
            description = "For students passionate about automotive design, manufacturing, and racing. The club participates in national-level competitions like BAJA, SUPRA, and EFFICYCLE.",
            leaders = "Dr. I. J. Reddy",
            contact = "sae@rcoem.edu",
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
            description = "Focuses on mindfulness, meditation, and spiritual well-being to help students manage stress and find inner balance through various sessions and talks.",
            leaders = "Prof. H. I. Kumar",
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
            description = "A multidisciplinary club for all tech enthusiasts. It provides a common platform for interdisciplinary technical activities and projects.",
            leaders = "Dr. S. T. Verma",
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
            description = "Dedicated to developing student's personality through community service. NSS is a voluntary association of young people in Colleges and Universities.",
            leaders = "Dr. P. Q. Rao",
            contact = "nss@rcoem.edu",
            keywords = "nss social service volunteering"
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
