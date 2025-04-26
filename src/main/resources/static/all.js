let _lastPopover = null;

function showPopover(element, widgetInfo) {
    hidePopover(element);
    const popover = new bootstrap.Popover(element, {
        title: 'Informacije iz JS-a', // Naslov postavljen iz JS-a

        // napravi link sa widgetInfo.debugLink
        content: `<a href="${widgetInfo.debugLink}" target="_blank"> ${widgetInfo.widgetId} </a>`,
        placement: 'right', // Gde da se prikaže u odnosu na element (top, bottom, left, right, auto)
        trigger: 'manual', // Manualno upravljanje prikazom
        html: true, // Da li da se tretira sadržaj kao HTML

        delay: { show: 0, hide: 1000 }, // Odloži skrivanje popovera
        // container: 'body'     // Opciono: prikaži popover na nivou body-ja da izbegne probleme sa pozicioniranjem unutar uskih elemenata
    });
    _lastPopover = popover;
    popover.show();


    console.log("show popover");
}

function hidePopover(element) {
    if (_lastPopover) {
        _lastPopover.hide();
        _lastPopover = null;
    }
}


// function hideCustomDebugTooltip() {
//     const tooltip = document.getElementById('custom-debug-tooltip');
//     if (tooltip) {
//         tooltip.parentNode.removeChild(tooltip);
//     }
// }


/**
 * @param widgetsInfo arrays of objects with id and name
 */
function addHoverEffect(widgetsInfo) {
    widgetsInfo.forEach(w => {
        console.log("Widget info", w);
        const element = document.getElementById(w.widgetId);
        if(!element) {
            console.error("Element with id " + w.widgetId + " not found");
            return;
        }
        if (element) {
            element.style.border = '2px solid transparent';
            element.style.transition = 'border 0.3s';

            element.addEventListener('mouseover', () => {
                element.style.border = '2px solid red';
                // showCustomDebugTooltip(element);
                showPopover(element, w);
            });

            element.addEventListener('mouseout', () => {
                element.style.border = '2px solid transparent';
                // hideCustomDebugTooltip();
              //  hidePopover(element);
            });
        }
    });
}

// Dodaj globalni event listener za skrivanje tooltip-a
// document.addEventListener('mouseover', (event) => {
//     if (!event.target.closest('[id]')) {
//         hideCustomDebugTooltip();
//     }
// });

function initDebug() {

    addHoverEffect(WEBOO_WIDGETS_INFO);
    // Dodaj globalni event listener za klik izvan popovera
    document.addEventListener('click', (event) => {
        if (_lastPopover && !event.target.closest('.popover') && !event.target.hasAttribute('id')) {
            _lastPopover.hide();
            _lastPopover = null;
        }
    });

}

document.addEventListener('DOMContentLoaded', initDebug);