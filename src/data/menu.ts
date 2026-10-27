import { images } from "./site";

export const menuCategories = [
  "Starters",
  "Vegetarian",
  "Non-Vegetarian",
  "Biryani & Rice",
  "Breads",
  "Desserts",
  "Beverages",
] as const;
export type MenuCategory = (typeof menuCategories)[number];

export interface MenuItem {
  id: string;
  name: string;
  description: string;
  /** Fictional demo price in INR */
  price: number;
  category: MenuCategory;
  veg: boolean;
  spice?: 1 | 2 | 3;
  image: string;
  featured?: boolean;
}

export const menu: MenuItem[] = [
  { id: "paneer-tikka", name: "Tandoori Paneer Tikka", description: "Cottage cheese, hung-curd marinade, charred peppers and mint chutney.", price: 395, category: "Starters", veg: true, spice: 2, image: images.tandoor, featured: true },
  { id: "amritsari-fish", name: "Amritsari Fish", description: "Gram-flour battered river fish with ajwain and chaat masala.", price: 475, category: "Starters", veg: false, spice: 2, image: images.spices },
  { id: "dahi-kebab", name: "Dahi Ke Kebab", description: "Silken yoghurt patties, cardamom and crisp onion.", price: 345, category: "Starters", veg: true, spice: 1, image: images.tandoor },
  { id: "dal-makhani", name: "Dal Makhani", description: "Black lentils slow-cooked overnight on embers, finished with cream.", price: 425, category: "Vegetarian", veg: true, spice: 1, image: images.dal, featured: true },
  { id: "palak-paneer", name: "Palak Paneer", description: "Fresh spinach, garlic tempering and soft paneer.", price: 445, category: "Vegetarian", veg: true, spice: 1, image: images.dal },
  { id: "baingan", name: "Baingan Bharta", description: "Fire-roasted aubergine mashed with onion, tomato and green chilli.", price: 395, category: "Vegetarian", veg: true, spice: 2, image: images.spices },
  { id: "butter-chicken", name: "Butter Chicken", description: "Tandoor chicken in a velvety tomato, fenugreek and butter gravy.", price: 545, category: "Non-Vegetarian", veg: false, spice: 1, image: images.hero, featured: true },
  { id: "laal-maas", name: "Laal Maas", description: "Rajasthani mutton curry with smoked Mathania chillies.", price: 645, category: "Non-Vegetarian", veg: false, spice: 3, image: images.hero },
  { id: "chicken-chettinad", name: "Chicken Chettinad", description: "Black pepper, kalpasi and coconut from the South.", price: 525, category: "Non-Vegetarian", veg: false, spice: 3, image: images.spices },
  { id: "hyderabadi-biryani", name: "Hyderabadi Chicken Biryani", description: "Dum-cooked basmati, saffron, fried onion and mint, sealed in a handi.", price: 595, category: "Biryani & Rice", veg: false, spice: 2, image: images.biryani, featured: true },
  { id: "subz-biryani", name: "Subz Dum Biryani", description: "Seasonal vegetables layered with kewra-scented rice.", price: 475, category: "Biryani & Rice", veg: true, spice: 1, image: images.biryani },
  { id: "jeera-rice", name: "Jeera Rice", description: "Basmati tempered with cumin and ghee.", price: 245, category: "Biryani & Rice", veg: true, image: images.biryani },
  { id: "garlic-naan", name: "Garlic Naan", description: "Tandoor-blistered with garlic butter and coriander.", price: 125, category: "Breads", veg: true, image: images.dal },
  { id: "laccha-paratha", name: "Laccha Paratha", description: "Flaky, layered whole-wheat bread.", price: 115, category: "Breads", veg: true, image: images.dal },
  { id: "gulab-jamun", name: "Gulab Jamun", description: "Warm milk dumplings in saffron-cardamom syrup with pistachio.", price: 225, category: "Desserts", veg: true, image: images.dessert, featured: true },
  { id: "phirni", name: "Kesar Phirni", description: "Ground-rice pudding set in clay, saffron and almond.", price: 245, category: "Desserts", veg: true, image: images.dessert },
  { id: "masala-chai", name: "Masala Chai", description: "Assam tea brewed with ginger and cardamom, served in kulhad.", price: 145, category: "Beverages", veg: true, image: images.drinks },
  { id: "mango-lassi", name: "Mango Lassi", description: "Alphonso mango and thick yoghurt.", price: 195, category: "Beverages", veg: true, image: images.drinks },
];

export function filterMenu(items: MenuItem[], category: MenuCategory | "All", query: string) {
  const q = query.trim().toLowerCase();
  return items.filter(
    (i) =>
      (category === "All" || i.category === category) &&
      (!q || i.name.toLowerCase().includes(q) || i.description.toLowerCase().includes(q)),
  );
}

export const formatINR = (n: number) => `₹${n.toLocaleString("en-IN")}`;
