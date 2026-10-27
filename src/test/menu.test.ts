import { describe, expect, it } from "vitest";
import { filterMenu, formatINR, menu } from "@/data/menu";

describe("menu filtering", () => {
  it("combines category and search", () => {
    const r = filterMenu(menu, "Biryani & Rice", "chicken");
    expect(r.map((i) => i.id)).toEqual(["hyderabadi-biryani"]);
  });
  it("returns nothing when no match", () => {
    expect(filterMenu(menu, "Desserts", "naan")).toHaveLength(0);
  });
  it("formats rupees", () => {
    expect(formatINR(1250)).toBe("₹1,250");
  });
});
