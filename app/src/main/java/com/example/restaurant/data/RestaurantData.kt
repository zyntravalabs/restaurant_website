package com.example.restaurant.data

import com.example.restaurant.R

enum class MenuCategory(val displayName: String) {
    ALL("All"),
    STARTERS("Starters"),
    VEGETARIAN("Vegetarian"),
    NON_VEGETARIAN("Non-Vegetarian"),
    BIRYANI_RICE("Biryani & Rice"),
    BREADS("Breads"),
    DESSERTS("Desserts"),
    BEVERAGES("Beverages")
}

data class MenuItem(
    val id: String,
    val name: String,
    val description: String,
    val price: Int,
    val category: MenuCategory,
    val veg: Boolean,
    val spice: Int? = null,
    val drawableRes: Int,
    val featured: Boolean = false
)

data class OpeningHour(
    val days: String,
    val hours: String
)

data class GalleryItem(
    val drawableRes: Int,
    val alt: String,
    val category: String,
    val wide: Boolean = false
)

data class FaqItem(
    val question: String,
    val answer: String
)

data class Pillar(
    val number: String,
    val title: String,
    val text: String
)

object RestaurantData {
    val businessName = "Ember & Spice"
    val tagline = "Indian Kitchen"
    val agency = "Zyntrava Labs"
    val email = "hello@example.com"
    val whatsapp = "" // Fictional demo
    val address = "Demo Address, Your City, India"

    val openingHours = listOf(
        OpeningHour("Monday", "Closed"),
        OpeningHour("Tuesday – Thursday", "12:00 – 15:00 · 18:30 – 23:00"),
        OpeningHour("Friday – Saturday", "12:00 – 15:30 · 18:30 – 23:30"),
        OpeningHour("Sunday", "12:00 – 16:00 · 18:30 – 22:30")
    )

    val menuItems = listOf(
        MenuItem(
            id = "paneer-tikka",
            name = "Tandoori Paneer Tikka",
            description = "Cottage cheese, hung-curd marinade, charred peppers and mint chutney.",
            price = 395,
            category = MenuCategory.STARTERS,
            veg = true,
            spice = 2,
            drawableRes = R.drawable.tandoor,
            featured = true
        ),
        MenuItem(
            id = "amritsari-fish",
            name = "Amritsari Fish",
            description = "Gram-flour battered river fish with ajwain and chaat masala.",
            price = 475,
            category = MenuCategory.STARTERS,
            veg = false,
            spice = 2,
            drawableRes = R.drawable.spices
        ),
        MenuItem(
            id = "dahi-kebab",
            name = "Dahi Ke Kebab",
            description = "Silken yoghurt patties, cardamom and crisp onion.",
            price = 345,
            category = MenuCategory.STARTERS,
            veg = true,
            spice = 1,
            drawableRes = R.drawable.tandoor
        ),
        MenuItem(
            id = "dal-makhani",
            name = "Dal Makhani",
            description = "Black lentils slow-cooked overnight on embers, finished with cream.",
            price = 425,
            category = MenuCategory.VEGETARIAN,
            veg = true,
            spice = 1,
            drawableRes = R.drawable.dal,
            featured = true
        ),
        MenuItem(
            id = "palak-paneer",
            name = "Palak Paneer",
            description = "Fresh spinach, garlic tempering and soft paneer.",
            price = 445,
            category = MenuCategory.VEGETARIAN,
            veg = true,
            spice = 1,
            drawableRes = R.drawable.dal
        ),
        MenuItem(
            id = "baingan",
            name = "Baingan Bharta",
            description = "Fire-roasted aubergine mashed with onion, tomato and green chilli.",
            price = 395,
            category = MenuCategory.VEGETARIAN,
            veg = true,
            spice = 2,
            drawableRes = R.drawable.spices
        ),
        MenuItem(
            id = "butter-chicken",
            name = "Butter Chicken",
            description = "Tandoor chicken in a velvety tomato, fenugreek and butter gravy.",
            price = 545,
            category = MenuCategory.NON_VEGETARIAN,
            veg = false,
            spice = 1,
            drawableRes = R.drawable.hero,
            featured = true
        ),
        MenuItem(
            id = "laal-maas",
            name = "Laal Maas",
            description = "Rajasthani mutton curry with smoked Mathania chillies.",
            price = 645,
            category = MenuCategory.NON_VEGETARIAN,
            veg = false,
            spice = 3,
            drawableRes = R.drawable.hero
        ),
        MenuItem(
            id = "chicken-chettinad",
            name = "Chicken Chettinad",
            description = "Black pepper, kalpasi and coconut from the South.",
            price = 525,
            category = MenuCategory.NON_VEGETARIAN,
            veg = false,
            spice = 3,
            drawableRes = R.drawable.spices
        ),
        MenuItem(
            id = "hyderabadi-biryani",
            name = "Hyderabadi Chicken Biryani",
            description = "Dum-cooked basmati, saffron, fried onion and mint, sealed in a handi.",
            price = 595,
            category = MenuCategory.BIRYANI_RICE,
            veg = false,
            spice = 2,
            drawableRes = R.drawable.biryani,
            featured = true
        ),
        MenuItem(
            id = "subz-biryani",
            name = "Subz Dum Biryani",
            description = "Seasonal vegetables layered with kewra-scented rice.",
            price = 475,
            category = MenuCategory.BIRYANI_RICE,
            veg = true,
            spice = 1,
            drawableRes = R.drawable.biryani
        ),
        MenuItem(
            id = "jeera-rice",
            name = "Jeera Rice",
            description = "Basmati tempered with cumin and ghee.",
            price = 245,
            category = MenuCategory.BIRYANI_RICE,
            veg = true,
            drawableRes = R.drawable.biryani
        ),
        MenuItem(
            id = "garlic-naan",
            name = "Garlic Naan",
            description = "Tandoor-blistered with garlic butter and coriander.",
            price = 125,
            category = MenuCategory.BREADS,
            veg = true,
            drawableRes = R.drawable.dal
        ),
        MenuItem(
            id = "laccha-paratha",
            name = "Laccha Paratha",
            description = "Flaky, layered whole-wheat bread.",
            price = 115,
            category = MenuCategory.BREADS,
            veg = true,
            drawableRes = R.drawable.dal
        ),
        MenuItem(
            id = "gulab-jamun",
            name = "Gulab Jamun",
            description = "Warm milk dumplings in saffron-cardamom syrup with pistachio.",
            price = 225,
            category = MenuCategory.DESSERTS,
            veg = true,
            drawableRes = R.drawable.dessert,
            featured = true
        ),
        MenuItem(
            id = "phirni",
            name = "Kesar Phirni",
            description = "Ground-rice pudding set in clay, saffron and almond.",
            price = 245,
            category = MenuCategory.DESSERTS,
            veg = true,
            drawableRes = R.drawable.dessert
        ),
        MenuItem(
            id = "masala-chai",
            name = "Masala Chai",
            description = "Assam tea brewed with ginger and cardamom, served in kulhad.",
            price = 145,
            category = MenuCategory.BEVERAGES,
            veg = true,
            drawableRes = R.drawable.drinks
        ),
        MenuItem(
            id = "mango-lassi",
            name = "Mango Lassi",
            description = "Alphonso mango and thick yoghurt.",
            price = 195,
            category = MenuCategory.BEVERAGES,
            veg = true,
            drawableRes = R.drawable.drinks
        )
    )

    val galleryItems = listOf(
        GalleryItem(R.drawable.hero, "Butter chicken, biryani and naan served in copper vessels beside open flame", "Dishes", true),
        GalleryItem(R.drawable.interior, "Candle-lit dining room with brass lanterns and white linen tables", "Interior", false),
        GalleryItem(R.drawable.tandoor, "Paneer tikka skewers lifted from a glowing clay tandoor", "Kitchen", false),
        GalleryItem(R.drawable.biryani, "Chicken biryani in a hammered copper handi with mint and fried onions", "Dishes", false),
        GalleryItem(R.drawable.spices, "Whole spices in brass bowls: cardamom, chilli, turmeric, star anise and saffron", "Kitchen", true),
        GalleryItem(R.drawable.dal, "Dal makhani with a cream swirl beside garlic naan", "Dishes", false),
        GalleryItem(R.drawable.dessert, "Gulab jamun in saffron syrup topped with pistachio", "Dishes", false),
        GalleryItem(R.drawable.drinks, "Masala chai in a clay kulhad next to a mango lassi", "Dishes", false)
    )

    val pillars = listOf(
        Pillar("01", "The tandoor", "A clay oven fed with charcoal for breads, tikkas and kebabs with real char."),
        Pillar("02", "The handi", "Biryanis and curries sealed and cooked slowly on dum, so flavours fold together."),
        Pillar("03", "The masala box", "Whole spices toasted and ground daily — never from a packet.")
    )

    val faqs = listOf(
        FaqItem("Do you take reservations?", "In this demo, you can send an enquiry. A real restaurant would confirm your table directly — nothing is booked automatically."),
        FaqItem("Are there vegetarian options?", "Yes — more than half the menu is vegetarian, and dishes are clearly labelled with green indicators."),
        FaqItem("Can you host private events?", "The concept includes a private lantern-lit room for up to forty guests.")
    )

    fun formatPrice(amount: Int): String {
        return "₹$amount"
    }
}
