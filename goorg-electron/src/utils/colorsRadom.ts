export const colors = [
   'blue',
   'orange',
   'green',
   'violet'
]

export function getColor(): string {
   const color = colors[Math.floor(Math.random() * colors.length)];
   return color;
}