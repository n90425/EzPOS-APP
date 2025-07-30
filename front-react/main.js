const {app, BrowserWindow} = require('electron');
const payh = require('path');

function createWindow() {
    const win = new BrowserWindow({
        width: 1024,
        height: 768,
        webPreferences: {
            nodeIntegration: true,
        },
    });

    win.loadURL('http://localhost:3000');
    win.webContents.openDevTools(); // Electron 창에서도 콘솔 보기
}

app.whenReady().then(createWindow);