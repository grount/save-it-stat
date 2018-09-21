'use strict'
const electron = require('electron')
const net = require('net')

const app = electron.app;
const BrowserWindow = electron.BrowserWindow
const ipc = electron.ipcMain

let mainWindow
let server

app.on('ready', _ => {
    mainWindow = new BrowserWindow({
        height: 1000,
        width: 1000
    })

    mainWindow.loadURL(`file://${__dirname}/index.html`)
    mainWindow.webContents.openDevTools()

    ipc.on('json-loaded', _ => {
        mainWindow.webContents.send('json-loaded', true)
    })

    server = net.createServer(socket => {
        console.log(`New client: ${socket.remoteAddress}:${socket.remotePort}`);
        mainWindow.webContents.send('json-updated', true)
        socket.destroy();
    })

    server.listen(5000, '127.0.0.1')

    mainWindow.on('closed', _ => {
        console.log('closed!')
        mainWindow = null
    })

    mainWindow.onbeforeunload = (e) => {
        // Prevent Command-R from unloading the window contents.
        e.returnValue = false
      }
})
