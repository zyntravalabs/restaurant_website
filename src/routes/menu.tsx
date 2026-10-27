import { createFileRoute } from "@tanstack/react-router";
import { Search } from "lucide-react";
import { useMemo, useState } from "react";
import { Container, CtaBand, PageHero } from "@/components/site/Layout";
import { DishCard } from "@/components/site/DishCard";
import { filterMenu, menu, menuCategories, type MenuCategory } from "@/data/menu";
import { images } from "@/data/site";

export const Route = createFileRoute("/menu")({
  head: () => ({
    meta: [
      { title: "Menu — Ember & Spice Indian Kitchen" },
      { name: "description", content: "Starters, curries, dum biryanis, tandoor breads, desserts and drinks. Demo menu with fictional prices." },
      { property: "og:title", content: "Menu — Ember & Spice" },
      { property: "og:description", content: "Browse and search the Ember & Spice demo menu." },
    ],
  }),
  component: MenuPage,
});

function MenuPage() {
  const [cat, setCat] = useState<MenuCategory | "All">("All");
  const [q, setQ] = useState("");
  const results = useMemo(() => filterMenu(menu, cat, q), [cat, q]);
  const reset = () => { setCat("All"); setQ(""); };
  const tabs: (MenuCategory | "All")[] = ["All", ...menuCategories];

  return (
    <>
      <PageHero eyebrow="The menu" title="Cooked over coal, copper and time." intro="Prices are fictional demonstration values in Indian rupees." image={images.biryani} />
      <Container className="py-16">
        <div className="mb-10 space-y-6">
          <label className="relative block max-w-md">
            <span className="sr-only">Search dishes</span>
            <Search className="pointer-events-none absolute left-4 top-1/2 h-4 w-4 -translate-y-1/2 text-muted-foreground" aria-hidden />
            <input value={q} onChange={(e) => setQ(e.target.value)} placeholder="Search dishes or ingredients" className="w-full border border-input bg-card py-3 pl-11 pr-4 text-foreground placeholder:text-muted-foreground focus:border-primary focus:outline-none" />
          </label>
          <div role="group" aria-label="Filter by category" className="flex flex-wrap gap-2">
            {tabs.map((t) => (
              <button key={t} aria-pressed={cat === t} onClick={() => setCat(t)} className={`border px-4 py-2 text-xs uppercase tracking-[0.15em] transition-colors ${cat === t ? "border-primary bg-primary text-primary-foreground" : "border-border text-muted-foreground hover:border-primary hover:text-foreground"}`}>
                {t}
              </button>
            ))}
          </div>
          <div className="flex items-center gap-4 text-sm text-muted-foreground" aria-live="polite">
            <span>{results.length} {results.length === 1 ? "dish" : "dishes"}</span>
            {(cat !== "All" || q) && <button onClick={reset} className="text-primary underline">Reset filters</button>}
          </div>
        </div>
        {results.length ? (
          <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3">{results.map((d) => <DishCard key={d.id} item={d} />)}</div>
        ) : (
          <div className="border border-dashed border-border py-20 text-center">
            <p className="font-display text-3xl text-foreground">No dishes match.</p>
            <p className="mt-2 text-muted-foreground">Try another word or category.</p>
            <button onClick={reset} className="mt-6 text-primary underline">Show all dishes</button>
          </div>
        )}
      </Container>
      <CtaBand />
    </>
  );
}
