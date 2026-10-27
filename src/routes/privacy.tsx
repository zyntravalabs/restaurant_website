import { createFileRoute } from "@tanstack/react-router";
import { Container } from "@/components/site/Layout";

export const Route = createFileRoute("/privacy")({
  head: () => ({
    meta: [
      { title: "Privacy — Ember & Spice Indian Kitchen" },
      { name: "description", content: "How the Ember & Spice demo website handles your information." },
      { property: "og:title", content: "Privacy — Ember & Spice" },
      { property: "og:description", content: "This demo does not collect or store personal data." },
    ],
  }),
  component: () => (
    <Container className="max-w-3xl space-y-6 py-24 text-muted-foreground">
      <p className="eyebrow">Privacy</p>
      <h1 className="text-section text-foreground">Privacy notice</h1>
      <p>This website is a portfolio demonstration. It has no forms that send data to a server, no accounts and no analytics or tracking added by us.</p>
      <p>Enquiry buttons open your own email app (or WhatsApp, if configured). Anything you send goes directly through that service and is governed by its policies.</p>
      <p>Questions about this concept can be sent to the email listed in the footer.</p>
    </Container>
  ),
});
