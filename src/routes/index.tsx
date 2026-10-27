import { createFileRoute, Link } from "@tanstack/react-router";
import { ButtonLink, Container, CtaBand, OpeningHours, SafeImage, SectionHeading } from "@/components/site/Layout";
import { DishCard } from "@/components/site/DishCard";
import { menu } from "@/data/menu";
import { gallery, images } from "@/data/site";

export const Route = createFileRoute("/")({
  head: () => ({
    meta: [
      { title: "Ember & Spice — Indian Kitchen | Restaurant Concept" },
      { name: "description", content: "A taste of India, crafted with fire. A premium Indian restaurant website concept by Zyntrava Labs." },
      { property: "og:title", content: "Ember & Spice — Indian Kitchen" },
      { property: "og:description", content: "A taste of India, crafted with fire. Restaurant website concept by Zyntrava Labs." },
    ],
  }),
  component: Home,
});

function Home() {
  const featured = menu.filter((m) => m.featured).slice(0, 3);
  return (
    <>
      <section className="relative min-h-[88vh] overflow-hidden">
        <img src={images.hero} alt="Indian feast of butter chicken, biryani and naan in copper beside open flame" width={1920} height={1088} className="absolute inset-0 h-full w-full object-cover" />
        <div className="absolute inset-0 bg-hero-overlay" />
        <Container className="relative flex min-h-[88vh] items-center py-24">
          <div className="reveal max-w-2xl space-y-7">
            <p className="eyebrow">Tandoor · Dum · Tadka</p>
            <h1 className="text-display text-foreground">A Taste of India, <em className="text-primary">Crafted with Fire.</em></h1>
            <p className="max-w-lg text-lg text-muted-foreground">Regional recipes cooked over charcoal and copper — from Awadhi biryanis to smoky Rajasthani curries.</p>
            <div className="flex flex-wrap gap-4">
              <ButtonLink to="/menu">Explore our menu</ButtonLink>
              <ButtonLink to="/visit" variant="ghost">Plan your visit</ButtonLink>
            </div>
          </div>
        </Container>
      </section>

      <section className="py-24">
        <Container className="grid items-center gap-14 md:grid-cols-2">
          <SafeImage src={images.spices} alt="Whole Indian spices in brass bowls" width={1024} height={1024} className="aspect-square w-full object-cover" />
          <div className="space-y-6">
            <p className="eyebrow">Our kitchen</p>
            <h2 className="text-section text-foreground">Spices ground each morning. Fires lit by noon.</h2>
            <p className="text-muted-foreground">Ember &amp; Spice imagines a kitchen where every masala is roasted and pounded in-house, breads are slapped against a clay tandoor to order, and biryanis are sealed with dough and cooked slowly on dum.</p>
            <Link to="/story" className="eyebrow inline-block border-b border-primary pb-1">Read our story</Link>
          </div>
        </Container>
      </section>

      <section className="bg-card py-24">
        <Container>
          <SectionHeading eyebrow="Signatures" title="Dishes worth the journey" />
          <div className="grid gap-6 md:grid-cols-3">{featured.map((d) => <DishCard key={d.id} item={d} />)}</div>
          <div className="mt-12"><ButtonLink to="/menu" variant="ghost">View full menu</ButtonLink></div>
        </Container>
      </section>

      <section className="relative overflow-hidden">
        <SafeImage src={images.interior} alt="Candle-lit dining room with brass lanterns" className="absolute inset-0 h-full w-full object-cover" />
        <div className="absolute inset-0 bg-hero-overlay" />
        <Container className="relative py-32">
          <div className="max-w-lg space-y-5">
            <p className="eyebrow">The experience</p>
            <h2 className="text-section text-foreground">Lantern light, brass and a long, slow dinner.</h2>
            <p className="text-muted-foreground">Seating for intimate dinners, family tables and private celebrations of up to forty guests.</p>
          </div>
        </Container>
      </section>

      <section className="py-24">
        <Container>
          <SectionHeading eyebrow="Gallery" title="From the fire" />
          <div className="grid grid-cols-2 gap-4 md:grid-cols-4">
            {gallery.slice(2, 6).map((g) => <SafeImage key={g.alt} src={g.src} alt={g.alt} className="aspect-square w-full object-cover" />)}
          </div>
          <div className="mt-10"><ButtonLink to="/gallery" variant="ghost">Open gallery</ButtonLink></div>
        </Container>
      </section>

      <section className="bg-card py-24">
        <Container className="grid gap-14 md:grid-cols-2">
          <SectionHeading eyebrow="Visit" title="Open six days a week" intro="Walk-ins welcome; enquiries recommended for weekends and groups." />
          <OpeningHours />
        </Container>
      </section>

      <CtaBand />
    </>
  );
}
