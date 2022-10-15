/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.tsx"
  ],
  theme: {
    extend: {
      colors: {
        gray: {
          300: '#94999D',
          400: '#EFEFEF',
        },
        blue: {
          200: '#BAC1FF',
        },
        "blue-dark": '#092942',
        "blue-dark-100": '#2F738E',
        "blue-dark-200": "#1D5267",
        "blue-primary": '#686EFF',
        "red-ligth": "#FF6868",
      },
      backgroundImage: {
        loginHero: 'url(/src/assets/bg.png)',
      },
      screens: {
        'xlmax': {'max': '1200px'},
        'mdmax': {'max': '950px'}
      }
    },
  },
  plugins: [],
}
