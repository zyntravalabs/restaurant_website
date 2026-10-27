import { Link } from "@tanstack/react-router";
import { Menu as MenuIcon, X } from "lucide-react";
import { useState, type ReactNode, type ImgHTMLAttributes } from "react";
import { business, navLinks, openingHours } from "@/data/site";

export function Container({ children, className = "" }: { children: ReactNode; className?: string }) {
  return <div className={`mx-auto w-full max-w-7xl px-5 md:px-10 ${className}`}>{children}</div>;
}

export function Banner() {
  return (
    <div className="border-b border-border bg-muted py-2 text-center text-xs text-muted-foreground">
      Website concept by <span className="text-primary">{business.agency}</span> — Ember &amp; Spice is a fictional restaurant.
    </div>
  );
}

export function Wordmark() {
  return (
    <Link to="/" className="leading-none">
      <span className="font-display text-2xl text-foreground">Ember <span className="text-primary">&amp;</span> Spice</span>
      <span className="block text-[0.6rem] uppercase tracking-[0.35em] text-muted-foreground">Indian Kitchen</span>
    </Link>
  );
}

export function ButtonLink({ to, children, variant = "primary" }: { to: "/" | "/menu" | "/visit" | "/gallery" | "/story"; children: ReactNode; variant?: "primary" | "ghost" }) {
  const cls =
    variant === "primary"
      ? "bg-primary text-primary-foreground hover:bg-foreground"
      : "border border-foreground/40 text-foreground hover:border-primary hover:text-primary";
  return (
    <Link to={to} className={`inline-flex items-center justify-center px-7 py-3.5 text-xs font-semibold uppercase tracking-[0.2em] transition-colors focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-ring ${cls}`}>
      {children}
    </Link>
  );
}

export function Header() {
  const [open, setOpen] = useState(false);
  return (
    <header className="sticky top-0 z-40 border-b border-border bg-background/90 backdrop-blur">
      <Container className="flex h-20 items-center justify-between">
        <Wordmark />
        <nav aria-label="Main" className="hidden items-center gap-8 lg:flex">
          {navLinks.map((l) => (
            <Link key={l.to} to={l.to} activeOptions={{ exact: true }} className="text-sm text-muted-foreground transition-colors hover:text-foreground" activeProps={{ className: "text-primary" }}>
              {l.label}
            </Link>
          ))}
        </nav>
        <div className="hidden lg:block"><ButtonLink to="/visit">Reserve a table</ButtonLink></div>
        <button className="p-2 text-foreground lg:hidden" aria-label={open ? "Close menu" : "Open menu"} aria-expanded={open} aria-controls="mobile-nav" onClick={() => setOpen(!open)}>
          {open ? <X /> : <MenuIcon />}
        </button>
      </Container>
      {open && (
        <nav id="mobile-nav" aria-label="Mobile" className="border-t border-border bg-background lg:hidden">
          <Container className="flex flex-col gap-1 py-4">
            {navLinks.map((l) => (
              <Link key={l.to} to={l.to} onClick={() => setOpen(false)} className="py-3 font-display text-2xl text-foreground" activeOptions={{ exact: true }} activeProps={{ className: "text-primary" }}>
                {l.label}
              </Link>
            ))}
          </Container>
        </nav>
      )}
    </header>
  );
}

export function Footer() {
  return (
    <footer className="border-t border-border bg-card">
      <Container className="grid gap-10 py-16 md:grid-cols-3">
        <div className="space-y-4">
          <Wordmark />
          <p className="max-w-xs text-sm text-muted-foreground">A portfolio concept for a modern Indian restaurant. All names, prices and details are fictional.</p>
        </div>
        <div>
          <p className="eyebrow mb-4">Hours</p>
          <OpeningHours compact />
        </div>
        <div className="space-y-2 text-sm">
          <p className="eyebrow mb-4">Explore</p>
          {navLinks.map((l) => <Link key={l.to} to={l.to} className="block text-muted-foreground hover:text-primary">{l.label}</Link>)}
          <Link to="/privacy" className="block text-muted-foreground hover:text-primary">Privacy</Link>
        </div>
      </Container>
      <div className="border-t border-border py-6 text-center text-xs text-muted-foreground">
        Designed &amp; built by <span className="text-primary">{business.agency}</span> · Want a site like this for your restaurant? <a className="underline hover:text-primary" href={`mailto:${business.email}`}>Get in touch</a>
      </div>
    </footer>
  );
}

export function OpeningHours({ compact = false }: { compact?: boolean }) {
  return (
    <dl className={compact ? "space-y-2 text-sm" : "divide-y divide-border"}>
      {openingHours.map((h) => (
        <div key={h.days} className={`flex justify-between gap-4 ${compact ? "" : "py-4"}`}>
          <dt className="text-foreground">{h.days}</dt>
          <dd className="text-right text-muted-foreground">{h.hours}</dd>
        </div>
      ))}
    </dl>
  );
}

export function SectionHeading({ eyebrow, title, intro, center = false }: { eyebrow: string; title: string; intro?: string; center?: boolean }) {
  return (
    <div className={`mb-12 max-w-2xl space-y-4 ${center ? "mx-auto text-center" : ""}`}>
      <p className="eyebrow">{eyebrow}</p>
      <h2 className="text-section text-foreground">{title}</h2>
      {intro && <p className="text-muted-foreground">{intro}</p>}
    </div>
  );
}

export function PageHero({ eyebrow, title, intro, image }: { eyebrow: string; title: string; intro: string; image: string }) {
  return (
    <section className="relative overflow-hidden">
      <img src={image} alt="" className="absolute inset-0 h-full w-full object-cover" />
      <div className="absolute inset-0 bg-hero-overlay" />
      <Container className="relative py-24 md:py-32">
        <div className="reveal max-w-2xl space-y-5">
          <p className="eyebrow">{eyebrow}</p>
          <h1 className="text-display text-foreground">{title}</h1>
          <p className="text-lg text-muted-foreground">{intro}</p>
        </div>
      </Container>
    </section>
  );
}

export function SafeImage(props: ImgHTMLAttributes<HTMLImageElement>) {
  const [failed, setFailed] = useState(false);
  if (failed) return <div role="img" aria-label={props.alt} className={`flex items-center justify-center bg-muted font-display text-2xl text-primary/60 ${props.className ?? ""}`}>E&amp;S</div>;
  return <img loading="lazy" {...props} onError={() => setFailed(true)} />;
}

export function CtaBand() {
  return (
    <section className="border-y border-border bg-card">
      <Container className="flex flex-col items-start justify-between gap-8 py-20 md:flex-row md:items-center">
        <div className="max-w-xl space-y-3">
          <p className="eyebrow">An evening by the fire</p>
          <h2 className="text-section text-foreground">Plan your visit to Ember &amp; Spice.</h2>
        </div>
        <div className="flex flex-wrap gap-4">
          <ButtonLink to="/visit">Plan your visit</ButtonLink>
          <ButtonLink to="/menu" variant="ghost">See the menu</ButtonLink>
        </div>
      </Container>
    </section>
  );
}
