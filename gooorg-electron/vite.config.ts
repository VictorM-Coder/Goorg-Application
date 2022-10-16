import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import electron from 'vite-electron-plugin'

// https://vitejs.dev/config/
export default defineConfig({
  base: './',
  plugins: [react()]
})
