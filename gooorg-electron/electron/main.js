const { app, BrowserWindow } = require('electron')
const { join } = require('path');
const { fork } = require('child_process');
const { execFile } = require('child_process');

//require('./requests');

if (!app.requestSingleInstanceLock()) {
  app.quit()
  process.exit(0)
}

let win = null

async function createWindow () {
  win = new BrowserWindow({
    title: 'Main window',
    width: 1024,
    height: 768,
    webPreferences: {
      nodeIntegration: true,
      worldSafeExecuteJavaScript: true,
      contextIsolation: true,
      preload: join(__dirname, "preload.js") // use a preload script
    },
    icon: join(__dirname, "../public/icon.ico")
  })

  if (app.isPackaged) {
    win.loadFile(join(__dirname, '../dist/index.html'))
  } else {
    win.loadURL('http://127.0.0.1:5173/')
  }
}

// const child = fork(join(__dirname, 'target/api.jar'), ['java -jar'], {
//   stdio: 'pipe'
// });

// child.on('error', (err) => {
//   console.log(err)
// });

var jarPath = app.getAppPath() + '\\target\\api.jar';
var child = require('child_process').spawn('java', ['-jar', jarPath, '']);

//console.log(child.exitCode);

execFile(join(__dirname, '\\script.sh'), (error, stdout, stderr) => {
  if (error) {
    console.error(`error: ${error.message}`);
    return;
  }
  if (stderr) {
    console.error(`stderr: ${stderr}`);
    return;
  }

  console.log(`stdout:\n${stdout}`);
});

app.whenReady().then(createWindow)

app.on('window-all-closed', () => {
  win = null
  if (process.platform !== 'darwin') app.quit()
  var kill = require('tree-kill');
  kill(child.pid);
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


