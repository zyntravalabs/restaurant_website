import { createFileRoute } from "@tanstack/react-router";
import { Container, CtaBand, PageHero, SafeImage } from "@/components/site/Layout";
import { images } from "@/data/site";

export const Route = createFileRoute("/story")({
  head: () => ({
    meta: [
      { title: "Our Story — Ember & Spice Indian Kitchen" },
      { name: "description", content: "The idea behind Ember & Spice: regional Indian cooking around the tandoor and dum handi." },
      { property: "og:title", content: "Our Story — Ember & Spice" },
      { property: "og:description", content: "Regional Indian cooking, crafted with fire." },
    ],
  }),
  component: Story,
});

const pillars = [
  { title: "The tandoor", text: "A clay oven fed with charcoal for breads, tikkas and kebabs with real char." },
  { title: "The handi", text: "Biryanis and curries sealed and cooked slowly on dum, so flavours fold together." },
  { title: "The masala box", text: "Whole spices toasted and ground daily — never from a packet." },
];

function Story() {
  return (
    <>
      <PageHero eyebrow="Our story" title="A kitchen built around the fire." intro="Ember & Spice is a restaurant concept created to show what a modern Indian dining brand can look like online." image={images.tandoor} />
      <Container className="grid gap-14 py-24 md:grid-cols-2 md:items-center">
        <div className="space-y-6 text-muted-foreground">
          <p className="eyebrow">The idea</p>
          <h2 className="text-section text-foreground">Regional India, one plate at a time.</h2>
          <p>The concept draws on home kitchens across the country — Punjab's tandoors, Lucknow's dum pots, Chettinad's pepper and Rajasthan's smoked chillies — and brings them to one warm, lantern-lit room.</p>
          <p>Every detail here, from the menu to the hours, is a sample for demonstration.</p>
        </div>
        <SafeImage src={images.interior} alt="Lantern-lit dining room" className="aspect-[4/5] w-full object-cover" />
      </Container>
      <section className="bg-card py-24">
        <Container className="grid gap-10 md:grid-cols-3">
          {pillars.map((p, i) => (
            <div key={p.title} className="space-y-3 border-t border-primary pt-6">
              <span className="font-display text-5xl text-primary/50">0{i + 1}</span>
              <h3 className="text-3xl text-foreground">{p.title}</h3>
              <p className="text-muted-foreground">{p.text}</p>
            </div>
          ))}
        </Container>
      </section>
      <CtaBand />
    </>
  );
}
