const URLVirus = {
    items: '/api/viruses'
};

const URLCapitals = {
    items: '/api/capitals'
};

const virusTable = (virus) =>
    `
                <tr>
                     <td>${virus.id}</td>
                      <td>${virus.name}</td>
                      <td>${virus.magnitude}</td>
                      <td>${virus.releasedOn}</td>
                      <td><a class="btn btn-light ml-3" href="/viruses/edit/${virus.id}">Edit</a></td>

                   <td>
                       <form class="mx-auto w-75" method="post" action="/viruses/show">
                       <button type="submit" name="id" id="id" value="${virus.id}" class="btn btn-light ml-3">Delete</button>
                       </form>
                   </td>
                  </tr>`
const virusThead = () =>
    `  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Magnitude</th>
                    <th scope="col">Released On</th>
                </tr>`

const capitalsThead = () =>
    `  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Name</th>
                    <th scope="col">Latitude</th>
                    <th scope="col">Longitude</th>
                </tr>`
const capitalsTable = (capitals) =>
    ` 
                     <tr>
                     <td>${capitals.id}</td>
                      <td>${capitals.name}</td>
                      <td>${capitals.latitude}</td>
                      <td>${capitals.longitude}</td>
                    
                  </tr>`

function showAllViruses() {
    fetch(URLVirus.items)
        .then(response => response.json())
        .then(viruses => {
            let result = '';
            viruses.forEach(virus => {
                let virusString = virusTable(virus);
                result += virusString;
            });
            document.getElementById("columns-view")
                .innerHTML = virusThead();
            document.getElementById("choice").innerHTML = "All Viruses";
            document.getElementById('table-view')
                .innerHTML = result;
            hideLabel();
        });
}

function showAllCapitals() {
    fetch(URLCapitals.items)
        .then(response => response.json())
        .then(capitals => {

            let result = '';
            capitals.forEach(capital => {
                let virusString = capitalsTable(capital);
                result += virusString;
            });
            document.getElementById("columns-view")
                .innerHTML = capitalsThead();
            document.getElementById("choice").innerHTML = "All Capitals";
            document.getElementById('table-view')
                .innerHTML = result;
            hideLabel();
        });
}

function hideLabel() {
    document.getElementById('label').innerHTML = "";
}

