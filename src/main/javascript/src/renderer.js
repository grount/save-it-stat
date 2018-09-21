const fs = require('fs')
const electron = require('electron')
const ipc = electron.ipcRenderer;
const filepath = "./src/elements.json";
let json;

readFile();

ipc.on('json-loaded', _ => {
    generateElements();
});

ipc.on('json-updated', _ => {
    readFile();
})

function generateElements() {
    $('#main-list').remove();
    let ul = createUnorderedList();
    generateListItems();

    function createUnorderedList() {
        let ul = document.createElement('ul');
        ul.className = "list-group";
        ul.id = "main-list";
        return ul;
    }

    function generateListItems() {
        if (json.elements) {
            json.elements.forEach((element, index, array) => {
                let list_item = document.createElement('li');
                list_item.className = "list-group-item";
                let text_node = document.createTextNode(element.content);
                list_item.appendChild(text_node);
                ul.appendChild(list_item);
                document.getElementById('list-container').appendChild(ul);
            });
        }
    }
}

function readFile() {
    fs.readFile(filepath, 'utf-8', (err, data) => {
        if (err) {
            alert("An error ocurred reading the file: " + err.message);
            return;
        }

        json = JSON.parse(data);
        ipc.send('json-loaded')
    })
}