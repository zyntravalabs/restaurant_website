import { createFileRoute } from "@tanstack/react-router";
import { Mail, MapPin, MessageCircle } from "lucide-react";
import { Container, OpeningHours, PageHero } from "@/components/site/Layout";
import { business, images, whatsappUrl } from "@/data/site";

export const Route = createFileRoute("/visit")({
  head: () => ({
    meta: [
      { title: "Visit & Enquiries — Ember & Spice Indian Kitchen" },
      { name: "description", content: "Opening hours, location and how to send a table enquiry to Ember & Spice (demo)." },
      { property: "og:title", content: "Visit Ember & Spice" },
      { property: "og:description", content: "Hours, location and table enquiries." },
    ],
  }),
  component: Visit,
});

const faqs = [
  { q: "Do you take reservations?", a: "In this demo, you can send an enquiry. A real restaurant would confirm your table directly — nothing is booked automatically." },
  { q: "Are there vegetarian options?", a: "Yes — more than half the menu is vegetarian, and dishes are clearly labelled." },
  { q: "Can you host private events?", a: "The concept includes a private room for up to forty guests." },
];

function Visit() {
  const wa = whatsappUrl("Hello Ember & Spice, I'd like to enquire about a table.");
  return (
    <>
      <PageHero eyebrow="Visit us" title="Pull up a chair by the fire." intro="This is a website concept — the details below are placeholders." image={images.interior} />
      <Container className="grid gap-16 py-24 md:grid-cols-2">
        <div>
          <p className="eyebrow mb-4">Opening hours</p>
          <OpeningHours />
        </div>
        <div className="space-y-8">
          <div className="space-y-3">
            <p className="eyebrow">Location</p>
            <p className="flex gap-3 text-foreground"><MapPin className="h-5 w-5 shrink-0 text-primary" aria-hidden />{business.addressLines.join(", ")}</p>
            {business.mapsUrl ? <a href={business.mapsUrl} target="_blank" rel="noreferrer" className="text-primary underline">Get directions</a> : <p className="text-sm text-muted-foreground">Directions will appear once a real address is configured.</p>}
          </div>
          <div className="space-y-4 border border-border bg-card p-8">
            <p className="eyebrow">Table enquiries</p>
            <h2 className="text-3xl text-foreground">Send us a note</h2>
            <p className="text-sm text-muted-foreground">Starting an enquiry does not confirm a booking.</p>
            <div className="flex flex-wrap gap-3">
              <a href={`mailto:${business.email}?subject=Table%20enquiry`} className="inline-flex items-center gap-2 bg-primary px-6 py-3 text-xs font-semibold uppercase tracking-[0.2em] text-primary-foreground hover:bg-foreground"><Mail className="h-4 w-4" />Email enquiry</a>
              {wa && <a href={wa} target="_blank" rel="noreferrer" className="inline-flex items-center gap-2 border border-foreground/40 px-6 py-3 text-xs font-semibold uppercase tracking-[0.2em] text-foreground hover:border-primary"><MessageCircle className="h-4 w-4" />WhatsApp</a>}
            </div>
          </div>
        </div>
      </Container>
      <section className="bg-card py-24">
        <Container className="max-w-3xl">
          <p className="eyebrow mb-6">Questions</p>
          {faqs.map((f) => (
            <details key={f.q} className="border-b border-border py-5">
              <summary className="cursor-pointer font-display text-2xl text-foreground">{f.q}</summary>
              <p className="mt-3 text-muted-foreground">{f.a}</p>
            </details>
          ))}
        </Container>
      </section>
    </>
  );
}
