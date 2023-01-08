const { app, BrowserWindow } = require('electron')
const { join } = require('path');
const { spawn } = require('child_process');

if (!app.requestSingleInstanceLock()) {
  app.quit();
  process.exit(0);
}

let win = null;
let apiJava = 0;

async function createWindow () {
  win = new BrowserWindow({
    title: 'Goorg',
    width: 1024,
    height: 768,
    webPreferences: {
      nodeIntegration: true,
      worldSafeExecuteJavaScript: true,
      preload: join(__dirname, "preload.js") 
    },
    icon: join(__dirname, "../public/icon.ico")
  })

  if (app.isPackaged) {
    const jarPath = join(process.resourcesPath, "./app.asar.unpacked/electron/target/goorg-java-0.0.1-SNAPSHOT.jar")
    apiJava = spawn('java', ['-jar', jarPath]).pid;
    win.loadFile(join(__dirname, '../dist/index.html'));
  } else { 
    const jarPath = join(__dirname, "/target/goorg-java-0.0.1-SNAPSHOT.jar")
    apiJava = spawn('java', ['-jar', jarPath]);
    win.loadURL('http://127.0.0.1:5173/');
  }
}

app.whenReady().then(createWindow)

app.on('window-all-closed', () => {
  win = null
  if (process.platform !== 'darwin') app.quit();
})

app.on('second-instance', () => {
  if (win) {
    // Focus on the main window if the user tried to open another
    if (win.isMinimized()) win.restore()
    win.focus()
  }
})

app.on('activate', () => {
  const allWindows = BrowserWindow.getAllWindows()
  if (allWindows.length) {
    allWindows[0].focus()
  } else {
    createWindow()
  }
})


