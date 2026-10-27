import hero from "@/assets/hero.jpg";
import interior from "@/assets/interior.jpg";
import spices from "@/assets/spices.jpg";
import tandoor from "@/assets/tandoor.jpg";
import biryani from "@/assets/biryani.jpg";
import dal from "@/assets/dal.jpg";
import dessert from "@/assets/dessert.jpg";
import drinks from "@/assets/drinks.jpg";

export const images = { hero, interior, spices, tandoor, biryani, dal, dessert, drinks };

export interface BusinessConfig {
  name: string;
  tagline: string;
  agency: string;
  /** Digits only with country code, e.g. "919800000000". Empty = WhatsApp hidden. */
  whatsapp: string;
  email: string;
  addressLines: string[];
  /** Only set once a real location exists. */
  mapsUrl: string;
  socials: { label: string; href: string }[];
}

export const business: BusinessConfig = {
  name: "Ember & Spice",
  tagline: "Indian Kitchen",
  agency: "Zyntrava Labs",
  whatsapp: "",
  email: "hello@example.com",
  addressLines: ["Demo Address, Your City", "India"],
  mapsUrl: "",
  socials: [],
};

export const whatsappUrl = (message: string) =>
  business.whatsapp ? `https://wa.me/${business.whatsapp}?text=${encodeURIComponent(message)}` : "";

export interface OpeningHour { days: string; hours: string }
export const openingHours: OpeningHour[] = [
  { days: "Monday", hours: "Closed" },
  { days: "Tuesday – Thursday", hours: "12:00 – 15:00 · 18:30 – 23:00" },
  { days: "Friday – Saturday", hours: "12:00 – 15:30 · 18:30 – 23:30" },
  { days: "Sunday", hours: "12:00 – 16:00 · 18:30 – 22:30" },
];

export interface NavLink { to: "/" | "/menu" | "/story" | "/gallery" | "/visit"; label: string }
export const navLinks: NavLink[] = [
  { to: "/", label: "Home" },
  { to: "/menu", label: "Menu" },
  { to: "/story", label: "Our Story" },
  { to: "/gallery", label: "Gallery" },
  { to: "/visit", label: "Visit Us" },
];

export type GalleryCategory = "Dishes" | "Kitchen" | "Interior";
export interface GalleryImage { src: string; alt: string; category: GalleryCategory; wide?: boolean }
export const gallery: GalleryImage[] = [
  { src: hero, alt: "Butter chicken, biryani and naan served in copper vessels beside open flame", category: "Dishes", wide: true },
  { src: interior, alt: "Candle-lit dining room with brass lanterns and white linen tables", category: "Interior" },
  { src: tandoor, alt: "Paneer tikka skewers lifted from a glowing clay tandoor", category: "Kitchen" },
  { src: biryani, alt: "Chicken biryani in a hammered copper handi with mint and fried onions", category: "Dishes" },
  { src: spices, alt: "Whole spices in brass bowls: cardamom, chilli, turmeric, star anise and saffron", category: "Kitchen", wide: true },
  { src: dal, alt: "Dal makhani with a cream swirl beside garlic naan", category: "Dishes" },
  { src: dessert, alt: "Gulab jamun in saffron syrup topped with pistachio", category: "Dishes" },
  { src: drinks, alt: "Masala chai in a clay kulhad next to a mango lassi", category: "Dishes" },
];
