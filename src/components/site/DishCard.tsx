import { Flame, Leaf } from "lucide-react";
import { formatINR, type MenuItem } from "@/data/menu";
import { SafeImage } from "./Layout";

export function DishCard({ item }: { item: MenuItem }) {
  return (
    <article className="group overflow-hidden border border-border bg-card transition-colors hover:border-primary/60">
      <div className="aspect-[4/3] overflow-hidden">
        <SafeImage src={item.image} alt={item.name} width={800} height={600} className="h-full w-full object-cover transition-transform duration-700 group-hover:scale-105" />
      </div>
      <div className="space-y-3 p-6">
        <div className="flex items-baseline justify-between gap-4">
          <h3 className="text-2xl text-foreground">{item.name}</h3>
          <span className="font-display text-xl text-primary">{formatINR(item.price)}</span>
        </div>
        <p className="text-sm text-muted-foreground">{item.description}</p>
        <div className="flex gap-3 text-xs text-muted-foreground">
          <span className="inline-flex items-center gap-1">
            <Leaf className={`h-3.5 w-3.5 ${item.veg ? "text-primary" : "text-accent"}`} aria-hidden />
            {item.veg ? "Vegetarian" : "Non-veg"}
          </span>
          {item.spice && (
            <span className="inline-flex items-center gap-0.5" aria-label={`Spice level ${item.spice} of 3`}>
              {Array.from({ length: item.spice }).map((_, i) => <Flame key={i} className="h-3.5 w-3.5 text-accent" aria-hidden />)}
            </span>
          )}
        </div>
      </div>
    </article>
  );
}
