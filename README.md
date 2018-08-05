# CallCenter

El programa se basó en la utilización de clases de java que ayudan en los casos concurrentes como lo son el ExeccutionService el cual me permite tener el control de los hilos o tareas asincronas generadas y junto con la interface Callebale me permite generar muchos llamados asincronos y luego en un futuro obtner los resultados.

# Clase CallCenter

Esta clase es el main de la aplicación con un ejemplo de 2 Operadores,1 Supervisor y 1 Director con la posibilidad de 10 tareas asincronas.

# Modelos

- Call: Objeto que contiene el identificador de la llamada, el estado en el que está y el tiempo que duró la llamada.
- Charge: Enumerado con los diferentes tipos de cargos que puede tener un empleado (Operador, Supervisor, Director).
- Director: Objeto que tine las mismas características del empleado solo quesu cargo es de Director.
- Employee: Objeto que contiene un identificador y el tipo de cargo que tiene un empleado.
- Operator: Objeto que tine las mismas características del empleado solo quesu cargo es de Operator.
- StatusCall: Estado de una llamada que puede ser incommingCall, Connected y Finished.
- Supervisor: Objeto que tine las mismas características del empleado solo quesu cargo es de Supervisor.

# Servicios

- Dispatcher: Interface que contiene la recepción de empleados, el despachamiento y relización de la llamada. 
- DispatcherImpl: Implementación de la interface Dispatcher con los cambios de estado de la llamada y determinación si el empleado está ocupado o no.
- ManageCall: Clase que recibe la llamadas y los empleados y inicia la ejecución de los llamados futuros.
- MyProperties: Componente que lee las propiedades desde el archivo application.properties ubiicado en src/main/resources

# Extra

1. No hay ningún empleado libre: Es el caso de prueba número 1 ya que solo hay 5 personas que pueden atender 10 llamadas, en este caso ya que los hilos son considerados como futuros, desde la llamada número 6 quedan en espera de que haya un empleado libre para así poder completar ese futuro.

2. Más de 10 llamadas concurrentes: Es el caso de prueba número 2, en donde se asigna al ExecutorService 10 hilos pero se hacen 15 llamadas, en este caso se ocupan los 10 hilos los cuales dependen de la dispponibilidad de empleados, pero al momnto de finalizar una llamada, este hilo se asigna a la siguiente llamada, para así dar abasto a todas las llammadas ejecutadas.
