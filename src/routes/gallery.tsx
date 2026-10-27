import { createFileRoute } from "@tanstack/react-router";
import { ChevronLeft, ChevronRight, X } from "lucide-react";
import { useCallback, useEffect, useRef, useState } from "react";
import { Container, PageHero, SafeImage } from "@/components/site/Layout";
import { gallery, images, type GalleryCategory } from "@/data/site";

export const Route = createFileRoute("/gallery")({
  head: () => ({
    meta: [
      { title: "Gallery — Ember & Spice Indian Kitchen" },
      { name: "description", content: "Dishes, the tandoor kitchen and the lantern-lit dining room." },
      { property: "og:title", content: "Gallery — Ember & Spice" },
      { property: "og:description", content: "Photos from the Ember & Spice restaurant concept." },
    ],
  }),
  component: GalleryPage,
});

const cats: (GalleryCategory | "All")[] = ["All", "Dishes", "Kitchen", "Interior"];

function GalleryPage() {
  const [cat, setCat] = useState<(typeof cats)[number]>("All");
  const [idx, setIdx] = useState<number | null>(null);
  const items = gallery.filter((g) => cat === "All" || g.category === cat);
  const lastTrigger = useRef<HTMLButtonElement | null>(null);
  const closeRef = useRef<HTMLButtonElement>(null);

  const close = useCallback(() => { setIdx(null); lastTrigger.current?.focus(); }, []);
  const step = useCallback((d: number) => setIdx((i) => (i === null ? i : (i + d + items.length) % items.length)), [items.length]);

  useEffect(() => {
    if (idx === null) return;
    closeRef.current?.focus();
    const onKey = (e: KeyboardEvent) => {
      if (e.key === "Escape") close();
      if (e.key === "ArrowRight") step(1);
      if (e.key === "ArrowLeft") step(-1);
    };
    window.addEventListener("keydown", onKey);
    document.body.style.overflow = "hidden";
    return () => { window.removeEventListener("keydown", onKey); document.body.style.overflow = ""; };
  }, [idx, close, step]);

  const current = idx !== null ? items[idx] : null;

  return (
    <>
      <PageHero eyebrow="Gallery" title="Smoke, brass and saffron." intro="A look inside the kitchen and dining room." image={images.spices} />
      <Container className="py-16">
        <div role="group" aria-label="Filter photos" className="mb-10 flex flex-wrap gap-2">
          {cats.map((c) => (
            <button key={c} aria-pressed={cat === c} onClick={() => setCat(c)} className={`border px-4 py-2 text-xs uppercase tracking-[0.15em] ${cat === c ? "border-primary bg-primary text-primary-foreground" : "border-border text-muted-foreground hover:border-primary"}`}>{c}</button>
          ))}
        </div>
        <div className="grid grid-cols-2 gap-4 md:grid-cols-3">
          {items.map((g, i) => (
            <button key={g.alt} onClick={(e) => { lastTrigger.current = e.currentTarget; setIdx(i); }} className={`group overflow-hidden focus-visible:outline-2 focus-visible:outline-ring ${g.wide ? "col-span-2 aspect-[2/1]" : "aspect-square"}`} aria-label={`Open photo: ${g.alt}`}>
              <SafeImage src={g.src} alt={g.alt} className="h-full w-full object-cover transition-transform duration-700 group-hover:scale-105" />
            </button>
          ))}
        </div>
      </Container>
      {current && (
        <div role="dialog" aria-modal="true" aria-label="Photo viewer" className="fixed inset-0 z-50 flex items-center justify-center bg-background/95 p-4" onClick={close}>
          <figure className="max-h-full max-w-5xl" onClick={(e) => e.stopPropagation()}>
            <img src={current.src} alt={current.alt} className="max-h-[80vh] w-auto object-contain" />
            <figcaption className="mt-4 text-center text-sm text-muted-foreground">{current.alt} · {idx! + 1}/{items.length}</figcaption>
          </figure>
          <button ref={closeRef} onClick={close} aria-label="Close" className="absolute right-5 top-5 p-2 text-foreground hover:text-primary"><X /></button>
          <button onClick={(e) => { e.stopPropagation(); step(-1); }} aria-label="Previous photo" className="absolute left-3 top-1/2 -translate-y-1/2 p-3 text-foreground hover:text-primary"><ChevronLeft className="h-8 w-8" /></button>
          <button onClick={(e) => { e.stopPropagation(); step(1); }} aria-label="Next photo" className="absolute right-3 top-1/2 -translate-y-1/2 p-3 text-foreground hover:text-primary"><ChevronRight className="h-8 w-8" /></button>
        </div>
      )}
    </>
  );
}
