export const colors = [
   '#ea580c',
   '#16a34a',
   '#1d4ed8',
   '#86198f',
   '#e11d48'
]

export function getColor(): string {
   const color = colors[Math.floor(Math.random() * colors.length)];
   return color;
}